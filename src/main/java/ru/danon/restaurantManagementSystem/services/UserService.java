package ru.danon.restaurantManagementSystem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.danon.restaurantManagementSystem.dto.UserRequestDto;
import ru.danon.restaurantManagementSystem.dto.UserResponseDto;
import ru.danon.restaurantManagementSystem.mappers.UserMapper;
import ru.danon.restaurantManagementSystem.models.User;
import ru.danon.restaurantManagementSystem.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserResponseDto getUserById(Long id) {
        return userMapper.toDto(findUser(id));
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto dto) {
        User user = userMapper.toEntity(dto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = findUser(id);
        userMapper.updateEntity(user, dto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id);
        }
        userRepository.deleteById(id);
    }

    private User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id));
    }
}
