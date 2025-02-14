package com.mv.presentation.streaming.controller;

import com.mv.presentation.streaming.controller.dto.OrderStatusCreateDTO;
import com.mv.presentation.streaming.controller.dto.OrderStatusDTO;
import com.mv.presentation.streaming.mapper.OrderStatusMapper;
import com.mv.presentation.streaming.service.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order-status")
@RequiredArgsConstructor
public class OrderStatusController {

    private final OrderStatusService service;

    private final OrderStatusMapper mapper;

    @PutMapping("/next")
    public ResponseEntity<Mono<OrderStatusDTO>> update(@RequestParam final Long orderId) {
        return ResponseEntity.ok(service.nextPhase(orderId).map(mapper::toDTO));
    }

    @GetMapping(path = "/track/{orderId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<OrderStatusDTO>> track(@PathVariable final Long orderId) {
        return ResponseEntity.ok(service.track(orderId).map(mapper::toDTO));
    }
}
