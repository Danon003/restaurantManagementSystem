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
import ru.danon.restaurantManagementSystem.dto.MenuItemRequestDto;
import ru.danon.restaurantManagementSystem.dto.MenuItemResponseDto;
import ru.danon.restaurantManagementSystem.services.MenuItemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu-items")
public class MenuItemController {
    private final MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<List<MenuItemResponseDto>> getAll() {
        return ResponseEntity.ok(menuItemService.getAllMenuItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(menuItemService.getMenuItemById(id));
    }

    @PostMapping
    public ResponseEntity<MenuItemResponseDto> create(@RequestBody MenuItemRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemService.createMenuItem(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponseDto> update(@PathVariable Long id, @RequestBody MenuItemRequestDto dto) {
        return ResponseEntity.ok(menuItemService.updateMenuItem(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
