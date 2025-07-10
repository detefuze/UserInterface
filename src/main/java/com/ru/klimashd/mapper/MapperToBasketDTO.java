package com.ru.klimashd.mapper;

import com.ru.klimashd.dto.BasketDTO;
import com.ru.klimashd.entities.Basket;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperToBasketDTO {

    private final MapperToProductDTO mapperToProductDTO;

    public MapperToBasketDTO(MapperToProductDTO mapperToProductDTO) {
        this.mapperToProductDTO = mapperToProductDTO;
    }

    public BasketDTO mapToBasketDTO(int customer_id, Basket basket) {
        BasketDTO basketDTO = new BasketDTO();
        basketDTO.setProduct_id(basket.getProduct().getId());
        basketDTO.setName(basket.getName());
        basketDTO.setOrder_amount(basket.getOrder_amount());
        basketDTO.setPrice(basket.getPrice());
        basketDTO.setCustomer_id(customer_id);

        basketDTO.setProduct(mapperToProductDTO
                .mapToProductDTO(basket.getProduct()));

        return basketDTO;
    }

    public List<BasketDTO> mapListToBasketDTO(int customer_id, List<Basket> basket) {
        List<BasketDTO> basketDTOList = new ArrayList<>();

        for (Basket pos : basket) {
            basketDTOList.add(mapToBasketDTO(customer_id, pos));
        }

        return basketDTOList;
    }
}