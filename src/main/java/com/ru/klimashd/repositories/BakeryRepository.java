package com.ru.klimashd.repositories;

import com.ru.klimashd.entities.Bakery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BakeryRepository extends JpaRepository<Bakery, Integer> {
    Bakery getBakeryById(int id);
}
