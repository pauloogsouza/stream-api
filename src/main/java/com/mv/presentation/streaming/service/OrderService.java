package com.mv.presentation.streaming.service;

import com.mv.presentation.streaming.domain.Order;
import com.mv.presentation.streaming.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    private final OrderStatusService statusService;

    @Transactional
    public Mono<Order> create(final Order order) {
        return repository.save(order).flatMap(it -> statusService.save(it).thenReturn(it));
    }

    @Transactional
    public Flux<Order> findAll() {
        return repository.findAll();
    }
}
