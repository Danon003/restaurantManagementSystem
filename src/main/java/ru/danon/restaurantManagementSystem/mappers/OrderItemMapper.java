package ru.danon.restaurantManagementSystem.mappers;

import org.springframework.stereotype.Component;
import ru.danon.restaurantManagementSystem.dto.OrderItemRequestDto;
import ru.danon.restaurantManagementSystem.dto.OrderItemResponseDto;
import ru.danon.restaurantManagementSystem.models.MenuItem;
import ru.danon.restaurantManagementSystem.models.Order;
import ru.danon.restaurantManagementSystem.models.OrderItem;

@Component
public class OrderItemMapper {

    public OrderItem toEntity(OrderItemRequestDto dto, Order order, MenuItem menuItem) {
        OrderItem orderItem = new OrderItem();
        updateEntity(orderItem, dto, order, menuItem);
        return orderItem;
    }

    public void updateEntity(OrderItem orderItem, OrderItemRequestDto dto, Order order, MenuItem menuItem) {
        orderItem.setQuantity(dto.quantity());
        orderItem.setPrice(dto.price());
        orderItem.setOrder(order);
        orderItem.setMenuItem(menuItem);
    }

    public OrderItemResponseDto toDto(OrderItem orderItem) {
        return new OrderItemResponseDto(
                orderItem.getId(),
                orderItem.getOrder() != null ? orderItem.getOrder().getId() : null,
                orderItem.getMenuItem() != null ? orderItem.getMenuItem().getId() : null,
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}
