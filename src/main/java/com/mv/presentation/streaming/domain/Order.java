package com.mv.presentation.streaming.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "orders")
public record Order(@Id Long id, String dishName, String customer, @CreatedDate LocalDateTime createdAt) {

}
