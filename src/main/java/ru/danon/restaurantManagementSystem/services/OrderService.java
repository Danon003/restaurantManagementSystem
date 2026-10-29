package ru.danon.restaurantManagementSystem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.danon.restaurantManagementSystem.dto.OrderRequestDto;
import ru.danon.restaurantManagementSystem.dto.OrderResponseDto;
import ru.danon.restaurantManagementSystem.mappers.OrderMapper;
import ru.danon.restaurantManagementSystem.models.Order;
import ru.danon.restaurantManagementSystem.models.User;
import ru.danon.restaurantManagementSystem.repositories.OrderRepository;
import ru.danon.restaurantManagementSystem.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public OrderResponseDto getOrderById(Long id) {
        return orderMapper.toDto(findOrder(id));
    }

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto dto) {
        Order order = orderMapper.toEntity(dto, findUser(dto.userId()));
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Transactional
    public OrderResponseDto updateOrder(Long id, OrderRequestDto dto) {
        Order order = findOrder(id);
        orderMapper.updateEntity(order, dto, findUser(dto.userId()));
        return orderMapper.toDto(orderRepository.save(order));
    }

    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id);
        }
        orderRepository.deleteById(id);
    }

    private Order findOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id));
    }

    private User findUser(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId is required");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + id));
    }
}
