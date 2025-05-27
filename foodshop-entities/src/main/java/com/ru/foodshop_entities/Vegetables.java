package com.ru.foodshop_entities;

import com.ru.enums.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "vegetables")
public class Vegetables extends Product {
    public Vegetables(ProductType productType) {
        super(ProductType.VEGETABLE);
    }

    public Vegetables() {
        super();
    }
}
