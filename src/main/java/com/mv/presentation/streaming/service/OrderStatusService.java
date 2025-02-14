package com.mv.presentation.streaming.service;

import com.mv.presentation.streaming.controller.dto.OrderStatusCreateDTO;
import com.mv.presentation.streaming.domain.Order;
import com.mv.presentation.streaming.domain.OrderStatus;
import com.mv.presentation.streaming.exception.StreamException;
import com.mv.presentation.streaming.mapper.OrderStatusMapper;
import com.mv.presentation.streaming.repository.OrderRepository;
import com.mv.presentation.streaming.repository.OrderStatusRepository;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class OrderStatusService {

    private final OrderStatusRepository repository;

    private final OrderRepository orderRepository;

    private final OrderStatusMapper mapper;

    private final ApplicationContext context;

    @Transactional
    public Mono<OrderStatus> save(final OrderStatus orderStatus) {
        return orderRepository.existsById(orderStatus.getOrderId())
                .filter(exists -> exists)
                .switchIfEmpty(Mono.error(new StreamException(HttpStatus.NOT_FOUND, "Order not found!")))
                .then(repository.existsByOrderId(orderStatus.getOrderId()))
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.error(new StreamException(HttpStatus.BAD_REQUEST, "Order status already exists!"));
                    }
                    return repository.save(orderStatus);
                });
    }

    @Transactional
    public Mono<OrderStatus> save(final Order order) {
        return context.getBean(OrderStatusService.class).save(mapper.toEntity(order));
    }

    @Transactional
    public Mono<OrderStatus> nextPhase(final Long orderId) {
        return repository.findFirstByOrderIdOrderByUpdatedAtDesc(orderId)
                .switchIfEmpty(Mono.error(new StreamException(HttpStatus.NOT_FOUND, "Order status not found!")))
                .flatMap(it -> repository.save(it.next()));
    }

    @Transactional
    public Flux<OrderStatus> track(final Long orderId) {
        return Flux.from(repository.findFirstByOrderIdOrderByUpdatedAtDesc(orderId))
                .switchIfEmpty(Mono.error(new StreamException(HttpStatus.NOT_FOUND, "Order status not found!")))
                .repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(5)))
                .distinctUntilChanged(OrderStatus::getStatus)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(ignored -> context.getBean(OrderStatusService.class).nextPhase(orderId).subscribe())
                .takeUntil(it -> it.getStatus().equals(OrderStatus.Status.DELIVERED));
    }
}
