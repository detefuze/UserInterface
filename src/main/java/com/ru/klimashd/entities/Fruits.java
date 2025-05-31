package com.ru.klimashd.entities;

import com.ru.klimashd.enums.ProductType;
import jakarta.persistence.*;

@Entity
@Table(name = "fruits")
public class Fruits extends Product {
    public Fruits() {
        this.productType = ProductType.FRUIT;
    }
}
