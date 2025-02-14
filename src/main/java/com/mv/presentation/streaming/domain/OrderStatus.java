package com.mv.presentation.streaming.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table(name = "orders_statuses")
public final class OrderStatus {

    @Id
    private Long id;

    private Long orderId;

    @Column("status_")
    private Status status;

    private LocalDateTime updatedAt;

    public OrderStatus next() {
        if (this.status == Status.DELIVERED) {
            return this;
        }
        this.id = null;
        this.status = Status.values()[this.status.ordinal() + 1];
        this.updatedAt = LocalDateTime.now();
        return this;
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public enum Status {
        ORDER_PLACED,
        PROCESSING,
        SHIPPED,
        DELIVERED;
    }
}
