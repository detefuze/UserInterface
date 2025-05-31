package com.ru.foodshop_entities;

import com.ru.enums.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "bakery")
public class Bakery extends Product {
    public Bakery() {
        this.productType = ProductType.BAKERY;
    }

}
