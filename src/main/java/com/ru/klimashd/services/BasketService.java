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

    public void addNewOrder(Basket basket) {
        if (basketRepository.findByName(basket.getName()).isPresent()) {
            Basket db_basket = basketRepository.findByName(basket.getName()).get();
            int current_amount = basketRepository.findByName(basket.getName()).get().getAmount();
            int summary_amount = current_amount+basket.getAmount();
            db_basket.setAmount(summary_amount);
            db_basket.setPrice((db_basket.getPrice()/current_amount)*summary_amount);
            basketRepository.save(db_basket);
        } else {
            basketRepository.save(basket);
        }
    }
}
