package com.mv.presentation.streaming.service;

import com.mv.presentation.streaming.controller.dto.DrunkPositionDTO;
import com.mv.presentation.streaming.mapper.DrunkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class DrunkService {

    private final DrunkMapper mapper;

    public Flux<DrunkPositionDTO> walk(int steps) {
        return Flux.range(0, steps)
                .map(step -> mapper.toDTO(step, steps -1))
                .delayElements(Duration.ofSeconds(1));
    }
}
