package com.ru.klimashd.repositories;

import com.ru.klimashd.entities.Basket;
import com.ru.klimashd.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {
    @Query("""
    SELECT b FROM Basket b 
    JOIN b.product p 
    WHERE b.name = :name 
    AND p.id = :productId 
    AND TYPE(p) = :productClass
    """)
    Optional<Basket> findByNameAndProductIdAndProductType(
            String name,
            Integer productId,
            Class<? extends Product> productClass
    );
}
