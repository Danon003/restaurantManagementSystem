package ru.danon.restaurantManagementSystem.dto;

import java.math.BigDecimal;

public record OrderItemResponseDto(
        Long id,
        Long orderId,
        Long menuItemId,
        Integer quantity,
        BigDecimal price
) {
}
