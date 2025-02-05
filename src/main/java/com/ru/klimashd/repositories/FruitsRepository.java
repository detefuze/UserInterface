package com.ru.klimashd.repositories;

import com.ru.klimashd.entities.Bakery;
import com.ru.klimashd.entities.Fruits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitsRepository extends JpaRepository<Fruits, Integer> {
    Fruits getFruitsById(int id);
}
