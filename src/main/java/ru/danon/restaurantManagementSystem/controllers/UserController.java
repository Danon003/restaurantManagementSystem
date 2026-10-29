package ru.danon.restaurantManagementSystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.danon.restaurantManagementSystem.dto.UserRequestDto;
import ru.danon.restaurantManagementSystem.dto.UserResponseDto;
import ru.danon.restaurantManagementSystem.services.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
