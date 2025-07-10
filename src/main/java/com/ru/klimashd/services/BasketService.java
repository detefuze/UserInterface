package com.ru.klimashd.services;

import com.ru.klimashd.entities.Basket;
import com.ru.klimashd.entities.Product;
import com.ru.klimashd.repositories.BasketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final Logger log = LoggerFactory.getLogger(BasketService.class);

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public List<Basket> getAllOrders() {
        return basketRepository.findAll();
    }

    public void addNewBasketPosition(Basket basket, Class<? extends Product> productClass) {
        if (basket.getProduct() == null) {
            throw new IllegalArgumentException("Product must not be null in Basket");
        }
        log.info("Finding existing products in Basket");
        // Ищем корзину по имени товара И id продукта
        Optional<Basket> existingBasket = basketRepository.findByNameAndProductIdAndProductType(
                basket.getName(),
                basket.getProduct().getId(),
                productClass
        );

        if (existingBasket.isPresent()) {
            Basket dbBasket = existingBasket.get();
            int newAmount = dbBasket.getOrder_amount() + basket.getOrder_amount();
            dbBasket.setOrder_amount(newAmount);
            dbBasket.setPrice((dbBasket.getPrice() / basket.getOrder_amount()) * newAmount);
            basketRepository.save(dbBasket);
        } else {
            basket.setPrice(basket.getPrice()* basket.getOrder_amount());
            basketRepository.save(basket);
        }
    }

    public void freeBasket() { basketRepository.deleteAll();}
}
