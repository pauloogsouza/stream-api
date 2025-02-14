package com.mv.presentation.streaming.mapper;

import com.mv.presentation.streaming.controller.dto.DrunkPositionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.Random;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = LocalDateTime.class
)
public interface DrunkMapper {

    Random random = new Random();

    @Mapping(target = "x", expression = "java(random.nextBoolean() ? 1 : -1)")
    @Mapping(target = "y", source = "step")
    @Mapping(target = "stepsLeft", expression = "java(total - step)")
    DrunkPositionDTO toDTO(int step, int total);
}
