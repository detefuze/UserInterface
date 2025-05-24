package com.ru.klimashd.entities;

import com.ru.klimashd.enums.ProductType;
import jakarta.persistence.*;

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
