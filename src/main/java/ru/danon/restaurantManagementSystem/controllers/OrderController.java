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
import ru.danon.restaurantManagementSystem.dto.OrderRequestDto;
import ru.danon.restaurantManagementSystem.dto.OrderResponseDto;
import ru.danon.restaurantManagementSystem.services.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> update(@PathVariable Long id, @RequestBody OrderRequestDto dto) {
        return ResponseEntity.ok(orderService.updateOrder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
