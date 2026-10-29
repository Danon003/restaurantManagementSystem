package ru.danon.restaurantManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danon.restaurantManagementSystem.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
