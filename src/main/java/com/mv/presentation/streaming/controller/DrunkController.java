package com.mv.presentation.streaming.controller;

import com.mv.presentation.streaming.controller.dto.DrunkPositionDTO;
import com.mv.presentation.streaming.service.DrunkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/drunk")
@RequiredArgsConstructor
public class DrunkController {

    private final DrunkService service;

    @GetMapping(value = "/walk", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<DrunkPositionDTO>> walk(@RequestParam int steps) {
        return ResponseEntity.ok(service.walk(steps));
    }
}
