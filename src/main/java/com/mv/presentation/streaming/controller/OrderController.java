package com.mv.presentation.streaming.controller;

import com.mv.presentation.streaming.controller.dto.OrderCreateDTO;
import com.mv.presentation.streaming.controller.dto.OrderDTO;
import com.mv.presentation.streaming.mapper.OrderMapper;
import com.mv.presentation.streaming.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    private final OrderMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Flux<OrderDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().map(mapper::toDTO));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<OrderDTO>> create(@RequestBody final OrderCreateDTO dto) {
        return service.create(mapper.toEntity(dto)).map(it -> ResponseEntity.ok(mapper.toDTO(it)));
    }
}
