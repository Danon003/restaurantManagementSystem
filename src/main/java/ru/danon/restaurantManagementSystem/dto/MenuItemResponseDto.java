package ru.danon.restaurantManagementSystem.dto;

import java.math.BigDecimal;

public record MenuItemResponseDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Long categoryId
) {
}
