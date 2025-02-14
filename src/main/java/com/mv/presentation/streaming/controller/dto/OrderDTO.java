package com.mv.presentation.streaming.controller.dto;

import java.time.LocalDateTime;

public record OrderDTO(Long id, String dishName, String customer, LocalDateTime createdAt) {

}
