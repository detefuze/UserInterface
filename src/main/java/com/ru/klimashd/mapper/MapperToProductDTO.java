package com.ru.klimashd.mapper;

import com.ru.klimashd.dto.ProductDTO;
import com.ru.klimashd.entities.Product;
import org.springframework.stereotype.Component;


@Component
public class MapperToProductDTO {

    public ProductDTO mapToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductType(product.getProductType());
        productDTO.setName(product.getName());
        productDTO.setAmount(product.getAmount());
        productDTO.setPrice(product.getPrice());

        return productDTO;
    }
}
