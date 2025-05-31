package com.ru.foodshop_entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ru.enums.ProductType;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(
        name = "global_product_seq",
        sequenceName = "global_product_seq",
        allocationSize = 1
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Fruits.class, name = "fruits"),
        @JsonSubTypes.Type(value = Vegetables.class, name = "vegetables"),
        @JsonSubTypes.Type(value = Bakery.class, name = "bakery"),
        @JsonSubTypes.Type(value = Dairy.class, name = "dairy")
})
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_product_seq")
    @Column(name = "id_product")
    protected Integer id;

    @Column
    protected String name;

    @Column
    protected Integer amount;

    @Column
    protected Integer price;

    @Transient
    protected ProductType productType;

    public Product(ProductType productType) {
        this.productType = productType;
    }

    public Product() {
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public ProductType getProductType() { return productType; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}

