package com.ru.klimashd.services;

import com.ru.klimashd.dto.BasketDTO;
import com.ru.klimashd.entities.Basket;
import com.ru.klimashd.entities.Product;
import com.ru.klimashd.repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ProductService {
    // Реализация jpaRepositories в RepositoryConfig
    private final Map<Class<? extends Product>, JpaRepository<? extends Product, Integer>> jpaRepositories;
    // Реализация baseRepositories в RepositoryConfig
    private final Map<Class<? extends Product>, ProductBaseRepository<?>> baseRepositories;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public Product getProductByIdAndProductClass(Integer id, Class<? extends Product> productClass) {
        return jpaRepositories.get(productClass).findById(id).orElse(null);
    }
    @Transactional
    public void updateProductsAmount(List<BasketDTO> basketDTOList) {
        for (BasketDTO basketDTO : basketDTOList) {
            reduceProductAmount(basketDTO.getProduct().getProductType().getProductClass(),
                                basketDTO.getProduct_id(),
                                basketDTO.getOrder_amount());
        }
    }

    public void reduceProductAmount(Class<? extends Product> productClass,
                                    Integer id,
                                    Integer orderAmount) {
        ProductBaseRepository<?> repository = baseRepositories.get(productClass);
        int updated = repository.reduceAmount(id, orderAmount);
        if (updated == 0) logger.error("Ошибка обновления таблицы");
    }
}
