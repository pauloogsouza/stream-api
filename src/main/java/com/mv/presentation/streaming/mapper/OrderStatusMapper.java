package com.mv.presentation.streaming.mapper;

import com.mv.presentation.streaming.controller.dto.OrderCreateDTO;
import com.mv.presentation.streaming.controller.dto.OrderDTO;
import com.mv.presentation.streaming.controller.dto.OrderStatusCreateDTO;
import com.mv.presentation.streaming.controller.dto.OrderStatusDTO;
import com.mv.presentation.streaming.domain.Order;
import com.mv.presentation.streaming.domain.OrderStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {LocalDateTime.class, OrderStatus.Status.class}
)
public interface OrderStatusMapper {

    OrderStatusDTO toDTO(OrderStatus entity);

    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(OrderStatus.Status.ORDER_PLACED)")
    OrderStatus toEntity(OrderStatusCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderId", source = "id")
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "status", expression = "java(OrderStatus.Status.ORDER_PLACED)")
    OrderStatus toEntity(Order order);
}
