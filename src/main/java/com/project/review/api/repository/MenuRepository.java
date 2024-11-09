package com.project.review.api.repository;

import com.project.review.api.model.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

   List<MenuEntity> findAllByRestaurantId(Long restaurantId);
}
