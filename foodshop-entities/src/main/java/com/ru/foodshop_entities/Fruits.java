package com.ru.foodshop_entities;


import com.ru.enums.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "fruits")
public class Fruits extends Product {
    public Fruits() {
        this.productType = ProductType.FRUIT;
    }
}
