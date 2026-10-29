package ru.danon.restaurantManagementSystem.mappers;

import org.springframework.stereotype.Component;
import ru.danon.restaurantManagementSystem.dto.MenuItemRequestDto;
import ru.danon.restaurantManagementSystem.dto.MenuItemResponseDto;
import ru.danon.restaurantManagementSystem.models.Category;
import ru.danon.restaurantManagementSystem.models.MenuItem;

@Component
public class MenuItemMapper {

    public MenuItem toEntity(MenuItemRequestDto dto, Category category) {
        MenuItem menuItem = new MenuItem();
        updateEntity(menuItem, dto, category);
        return menuItem;
    }

    public void updateEntity(MenuItem menuItem, MenuItemRequestDto dto, Category category) {
        menuItem.setName(dto.name());
        menuItem.setDescription(dto.description());
        menuItem.setPrice(dto.price());
        menuItem.setCategory(category);
    }

    public MenuItemResponseDto toDto(MenuItem menuItem) {
        return new MenuItemResponseDto(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getCategory() != null ? menuItem.getCategory().getId() : null
        );
    }
}
