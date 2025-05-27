package com.ru.foodshop_entities;

import com.ru.enums.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "dairy")
public class Dairy extends Product {
    public Dairy(ProductType productType) {
        super(ProductType.DAIRY);
    }

    public Dairy() {
        super();
    }
}
