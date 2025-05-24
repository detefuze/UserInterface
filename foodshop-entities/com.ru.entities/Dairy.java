package com.ru.klimashd.entities;

import com.ru.klimashd.enums.ProductType;
import jakarta.persistence.*;

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
