package com.ru.klimashd.entities;

import com.ru.klimashd.enums.ProductType;
import jakarta.persistence.*;

@Entity
@Table(name = "vegetables")
public class Vegetables extends Product {
    public Vegetables() {
        this.productType = ProductType.VEGETABLE;
    }
}
