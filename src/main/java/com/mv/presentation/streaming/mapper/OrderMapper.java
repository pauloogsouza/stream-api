package com.mv.presentation.streaming.mapper;

import com.mv.presentation.streaming.controller.dto.OrderCreateDTO;
import com.mv.presentation.streaming.controller.dto.OrderDTO;
import com.mv.presentation.streaming.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = LocalDateTime.class
)
public interface OrderMapper {

    OrderDTO toDTO(Order order);

    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Order toEntity(OrderCreateDTO dto);
}
