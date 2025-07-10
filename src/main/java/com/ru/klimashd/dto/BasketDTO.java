package com.ru.klimashd.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BasketDTO {

    private String name;
    private Integer order_amount;
    private Integer price;
    private Integer product_id;
    private Integer customer_id;
    private ProductDTO product;

}
