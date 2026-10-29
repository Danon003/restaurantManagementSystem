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
import ru.danon.restaurantManagementSystem.dto.OrderItemRequestDto;
import ru.danon.restaurantManagementSystem.dto.OrderItemResponseDto;
import ru.danon.restaurantManagementSystem.services.OrderItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemResponseDto>> getAll() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getOrderItemById(id));
    }

    @PostMapping
    public ResponseEntity<OrderItemResponseDto> create(@RequestBody OrderItemRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemService.createOrderItem(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemResponseDto> update(@PathVariable Long id, @RequestBody OrderItemRequestDto dto) {
        return ResponseEntity.ok(orderItemService.updateOrderItem(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
