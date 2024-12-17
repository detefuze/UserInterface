package com.ru.klimashd.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "dairy")
public class Dairy {
    @PrimaryKeyJoinColumn(name = "id_product")

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

    protected Dairy() {
    }

    public Dairy(String name, int amount, int price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public int getId_product() {
        return id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
