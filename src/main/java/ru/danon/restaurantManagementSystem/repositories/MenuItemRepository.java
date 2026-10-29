package ru.danon.restaurantManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.danon.restaurantManagementSystem.models.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
