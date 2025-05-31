package com.ru.klimashd.entities;

import com.ru.klimashd.enums.ProductType;
import jakarta.persistence.*;

@Entity
@Table(name = "bakery")
public class Bakery extends Product {
    public Bakery() {
        this.productType = ProductType.BAKERY;
    }

}
