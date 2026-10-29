package ru.danon.restaurantManagementSystem.dto;

import ru.danon.restaurantManagementSystem.enums.UserRole;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        UserRole role,
        LocalDateTime createdAt
) {
}
