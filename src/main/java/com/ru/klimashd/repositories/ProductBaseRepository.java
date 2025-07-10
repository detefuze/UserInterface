package com.ru.klimashd.repositories;

import com.ru.klimashd.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ProductBaseRepository<T extends Product> {
    @Modifying
    @Query("UPDATE #{#entityName} e SET e.amount = e.amount - :orderAmount " +
            "WHERE e.id = :id AND e.amount >= :orderAmount")
            Integer reduceAmount(@Param("id") Integer id, @Param("orderAmount") Integer orderAmount);
}
