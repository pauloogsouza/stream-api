package com.mv.presentation.streaming.repository;

import com.mv.presentation.streaming.domain.OrderStatus;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OrderStatusRepository extends ReactiveCrudRepository<OrderStatus, Long> {

    Mono<OrderStatus> findByOrderId(Long orderId);

    Mono<Boolean> existsByOrderId(Long orderId);

    Mono<Boolean> existsNotByOrderId(Long orderId);

    Mono<OrderStatus> findFirstByOrderIdOrderByUpdatedAtDesc(Long orderId);

    Flux<OrderStatus> findByOrderIdOrderByUpdatedAtAsc(Long orderId);

    Flux<OrderStatus> findByOrderIdOrderByUpdatedAtDesc(Long orderId);
}
