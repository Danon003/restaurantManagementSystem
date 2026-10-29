package ru.danon.restaurantManagementSystem.mappers;

import org.springframework.stereotype.Component;
import ru.danon.restaurantManagementSystem.dto.CategoryRequestDto;
import ru.danon.restaurantManagementSystem.dto.CategoryResponseDto;
import ru.danon.restaurantManagementSystem.models.Category;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryRequestDto dto) {
        Category category = new Category();
        updateEntity(category, dto);
        return category;
    }

    public void updateEntity(Category category, CategoryRequestDto dto) {
        category.setName(dto.name());
    }

    public CategoryResponseDto toDto(Category category) {
        return new CategoryResponseDto(category.getId(), category.getName());
    }
}
