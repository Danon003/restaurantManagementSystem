package ru.danon.restaurantManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danon.restaurantManagementSystem.models.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
