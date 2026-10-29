package ru.danon.restaurantManagementSystem.dto;

import ru.danon.restaurantManagementSystem.enums.OrderStatus;

public record OrderRequestDto(
        OrderStatus status,
        Long userId
) {
}
