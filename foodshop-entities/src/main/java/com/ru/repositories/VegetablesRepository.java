package com.ru.repositories;

import com.ru.foodshop_entities.Vegetables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface VegetablesRepository extends JpaRepository<Vegetables, Integer> {
    Optional<Vegetables> findVegetablesById(int id);
}
