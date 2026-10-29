package ru.danon.restaurantManagementSystem.dto;

import ru.danon.restaurantManagementSystem.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderResponseDto(
        Long id,
        OrderStatus status,
        Long userId,
        LocalDateTime createdAt
) {
}
