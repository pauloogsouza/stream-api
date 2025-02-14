package com.mv.presentation.streaming.controller.dto;

import com.mv.presentation.streaming.domain.OrderStatus;

import java.time.LocalDateTime;

public record OrderStatusDTO(Long id, Long orderId, OrderStatus.Status status, LocalDateTime updatedAt) {

}
