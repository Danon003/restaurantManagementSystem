package ru.danon.restaurantManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danon.restaurantManagementSystem.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
