package ru.danon.restaurantManagementSystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.danon.restaurantManagementSystem.enums.UserRole;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов")
    @Column(name = "username", unique = true)
    private String username;

    @NotEmpty
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role = UserRole.WAITER;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    public User() {}
}

