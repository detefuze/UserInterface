package com.ru.klimashd.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "basket")
public class Basket{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String name;

    @Column(name = "amount")
    private Integer order_amount;

    @Column
    private Integer price;

    @ManyToOne()
    @JoinColumn(name = "id_product", referencedColumnName = "id_product")
    private Product product;

    public Basket(String name, Integer order_amount, Integer price) {
        this.name = name;
        this.order_amount = order_amount;
        this.price = price;
    }

    public Basket() {}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(Integer order_amount) {
        this.order_amount = order_amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
