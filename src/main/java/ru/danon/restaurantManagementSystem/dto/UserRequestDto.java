package ru.danon.restaurantManagementSystem.dto;

import ru.danon.restaurantManagementSystem.enums.UserRole;

public record UserRequestDto(
        String username,
        String email,
        String password,
        UserRole role
) {
}
