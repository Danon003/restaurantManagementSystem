package ru.danon.restaurantManagementSystem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.danon.restaurantManagementSystem.dto.MenuItemRequestDto;
import ru.danon.restaurantManagementSystem.dto.MenuItemResponseDto;
import ru.danon.restaurantManagementSystem.mappers.MenuItemMapper;
import ru.danon.restaurantManagementSystem.models.Category;
import ru.danon.restaurantManagementSystem.models.MenuItem;
import ru.danon.restaurantManagementSystem.repositories.CategoryRepository;
import ru.danon.restaurantManagementSystem.repositories.MenuItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final CategoryRepository categoryRepository;
    private final MenuItemMapper menuItemMapper;

    public List<MenuItemResponseDto> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(menuItemMapper::toDto)
                .toList();
    }

    public MenuItemResponseDto getMenuItemById(Long id) {
        return menuItemMapper.toDto(findMenuItem(id));
    }

    @Transactional
    public MenuItemResponseDto createMenuItem(MenuItemRequestDto dto) {
        MenuItem menuItem = menuItemMapper.toEntity(dto, findCategory(dto.categoryId()));
        return menuItemMapper.toDto(menuItemRepository.save(menuItem));
    }

    @Transactional
    public MenuItemResponseDto updateMenuItem(Long id, MenuItemRequestDto dto) {
        MenuItem menuItem = findMenuItem(id);
        menuItemMapper.updateEntity(menuItem, dto, findCategory(dto.categoryId()));
        return menuItemMapper.toDto(menuItemRepository.save(menuItem));
    }

    @Transactional
    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu item not found: " + id);
        }
        menuItemRepository.deleteById(id);
    }

    private MenuItem findMenuItem(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu item not found: " + id));
    }

    private Category findCategory(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "categoryId is required");
        }
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found: " + id));
    }
}
