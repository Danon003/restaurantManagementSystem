package ru.danon.restaurantManagementSystem.mappers;

import org.springframework.stereotype.Component;
import ru.danon.restaurantManagementSystem.dto.OrderRequestDto;
import ru.danon.restaurantManagementSystem.dto.OrderResponseDto;
import ru.danon.restaurantManagementSystem.models.Order;
import ru.danon.restaurantManagementSystem.models.User;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequestDto dto, User user) {
        Order order = new Order();
        updateEntity(order, dto, user);
        return order;
    }

    public void updateEntity(Order order, OrderRequestDto dto, User user) {
        if (dto.status() != null) {
            order.setStatus(dto.status());
        }
        order.setUser(user);
    }

    public OrderResponseDto toDto(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getStatus(),
                order.getUser() != null ? order.getUser().getId() : null,
                order.getCreated_at()
        );
    }
}
