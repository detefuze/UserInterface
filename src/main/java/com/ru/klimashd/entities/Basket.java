package com.ru.klimashd.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "basket")
public class Basket {
//    @OneToOne(mappedBy = "basket")
//    private Bakery bakery;
//
//    @OneToOne(mappedBy = "basket")
//    private Fruits fruits;
//
//    @OneToOne(mappedBy = "basket")
//    private Vegetables vegetables;
//
//    @OneToOne(mappedBy = "basket")
//    private Dairy dairy;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private int id_product;

    @Column
    private String name;

    @Column
    private int amount;

    @Column
    private int price;

    protected Basket() {
    }

    public int getId_product() {
        return id_product;
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
