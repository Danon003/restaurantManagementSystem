package ru.danon.restaurantManagementSystem.dto;

import java.math.BigDecimal;

public record OrderItemRequestDto(
        Long orderId,
        Long menuItemId,
        Integer quantity,
        BigDecimal price
) {
}
