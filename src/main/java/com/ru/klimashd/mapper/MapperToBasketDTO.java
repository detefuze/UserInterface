package com.ru.klimashd.mapper;

import com.ru.klimashd.dto.BasketDTO;
import com.ru.klimashd.entities.Basket;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperToBasketDTO {

    public BasketDTO mapToBasketDTO(Basket basket) {
        BasketDTO basketDTO = new BasketDTO();
        basketDTO.setProduct_id(basket.getProduct().getId());
        basketDTO.setName(basket.getName());
        basketDTO.setOrder_amount(basket.getOrder_amount());
        basketDTO.setPrice(basket.getPrice());
        basketDTO.setCustomer_id(basket.getId()); // TODO передать правильный id

        basketDTO.setProduct(basket.getProduct());

        return basketDTO;
    }

    public List<BasketDTO> mapListToBasketDTO(List<Basket> basket) {
        List<BasketDTO> basketDTOList = new ArrayList<>();

        for (Basket pos : basket) {
            basketDTOList.add(mapToBasketDTO(pos));
        }

        return basketDTOList;
    }
}