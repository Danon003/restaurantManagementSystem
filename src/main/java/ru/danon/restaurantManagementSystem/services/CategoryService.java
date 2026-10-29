package ru.danon.restaurantManagementSystem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.danon.restaurantManagementSystem.dto.CategoryRequestDto;
import ru.danon.restaurantManagementSystem.dto.CategoryResponseDto;
import ru.danon.restaurantManagementSystem.mappers.CategoryMapper;
import ru.danon.restaurantManagementSystem.models.Category;
import ru.danon.restaurantManagementSystem.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    public CategoryResponseDto getCategoryById(Long id) {
        return categoryMapper.toDto(findCategory(id));
    }

    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto dto) {
        Category category = categoryMapper.toEntity(dto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Transactional
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto dto) {
        Category category = findCategory(id);
        categoryMapper.updateEntity(category, dto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Transactional
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found: " + id);
        }
        categoryRepository.deleteById(id);
    }

    private Category findCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found: " + id));
    }
}
