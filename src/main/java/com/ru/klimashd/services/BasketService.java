package com.ru.klimashd.services;

import com.ru.klimashd.entities.Basket;
import com.ru.klimashd.repositories.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public List<Basket> getAllOrders() {
        return basketRepository.findAll();
    }
}
