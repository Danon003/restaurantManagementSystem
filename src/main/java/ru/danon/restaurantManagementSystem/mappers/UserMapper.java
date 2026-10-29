package ru.danon.restaurantManagementSystem.mappers;

import org.springframework.stereotype.Component;
import ru.danon.restaurantManagementSystem.dto.UserRequestDto;
import ru.danon.restaurantManagementSystem.dto.UserResponseDto;
import ru.danon.restaurantManagementSystem.models.User;

@Component
public class UserMapper {

    public User toEntity(UserRequestDto dto) {
        User user = new User();
        updateEntity(user, dto);
        return user;
    }

    public void updateEntity(User user, UserRequestDto dto) {
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        if (dto.role() != null) {
            user.setRole(dto.role());
        }
    }

    public UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getCreated_at()
        );
    }
}
