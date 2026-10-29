package ru.danon.restaurantManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danon.restaurantManagementSystem.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
