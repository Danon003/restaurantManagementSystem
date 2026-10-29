package ru.danon.restaurantManagementSystem.dto;

import java.math.BigDecimal;

public record MenuItemRequestDto(
        String name,
        String description,
        BigDecimal price,
        Long categoryId
) {
}
