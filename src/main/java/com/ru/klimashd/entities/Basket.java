package com.ru.klimashd.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id_product")
    private int id;

    @Column
    private String productType;

    @Column
    private String name;

    @Column
    private int amount;

    @Column
    private int price;

    Basket() {
    }

    public Basket(String productType, String name, int amount, int price) {
        this.productType = productType;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public int getId_product() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }
}
