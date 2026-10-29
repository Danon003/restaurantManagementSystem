package ru.danon.restaurantManagementSystem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.danon.restaurantManagementSystem.dto.OrderItemRequestDto;
import ru.danon.restaurantManagementSystem.dto.OrderItemResponseDto;
import ru.danon.restaurantManagementSystem.mappers.OrderItemMapper;
import ru.danon.restaurantManagementSystem.models.MenuItem;
import ru.danon.restaurantManagementSystem.models.Order;
import ru.danon.restaurantManagementSystem.models.OrderItem;
import ru.danon.restaurantManagementSystem.repositories.MenuItemRepository;
import ru.danon.restaurantManagementSystem.repositories.OrderItemRepository;
import ru.danon.restaurantManagementSystem.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderItemMapper orderItemMapper;

    public List<OrderItemResponseDto> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    public OrderItemResponseDto getOrderItemById(Long id) {
        return orderItemMapper.toDto(findOrderItem(id));
    }

    @Transactional
    public OrderItemResponseDto createOrderItem(OrderItemRequestDto dto) {
        OrderItem orderItem = orderItemMapper.toEntity(dto, findOrder(dto.orderId()), findMenuItem(dto.menuItemId()));
        return orderItemMapper.toDto(orderItemRepository.save(orderItem));
    }

    @Transactional
    public OrderItemResponseDto updateOrderItem(Long id, OrderItemRequestDto dto) {
        OrderItem orderItem = findOrderItem(id);
        orderItemMapper.updateEntity(orderItem, dto, findOrder(dto.orderId()), findMenuItem(dto.menuItemId()));
        return orderItemMapper.toDto(orderItemRepository.save(orderItem));
    }

    @Transactional
    public void deleteOrderItem(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item not found: " + id);
        }
        orderItemRepository.deleteById(id);
    }

    private OrderItem findOrderItem(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item not found: " + id));
    }

    private Order findOrder(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "orderId is required");
        }
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id));
    }

    private MenuItem findMenuItem(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "menuItemId is required");
        }
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu item not found: " + id));
    }
}
