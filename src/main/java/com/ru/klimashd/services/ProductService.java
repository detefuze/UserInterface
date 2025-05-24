package com.ru.klimashd.services;

import com.ru.klimashd.entities.Product;
import com.ru.klimashd.enums.ProductType;
import com.ru.klimashd.repositories.BakeryRepository;
import com.ru.klimashd.repositories.DairyRepository;
import com.ru.klimashd.repositories.FruitsRepository;
import com.ru.klimashd.repositories.VegetablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {
    private final Map<String, JpaRepository<? extends Product, Integer>> repositories;

    public ProductService(BakeryRepository bakeryRepository,
                          DairyRepository dairyRepository,
                          FruitsRepository fruitsRepository,
                          VegetablesRepository vegetablesRepository) {
        this.repositories = Map.of(
                ProductType.FRUIT.getProductType(), fruitsRepository,
                ProductType.VEGETABLE.getProductType(), vegetablesRepository,
                ProductType.DAIRY.getProductType(), dairyRepository,
                ProductType.BAKERY.getProductType(), bakeryRepository
        );
    }

    public JpaRepository<? extends Product, Integer> getRepositoryByProductType(
            String productType) {
        return repositories.get(productType);
    }

    public Product getProductById(Integer id, String productType) {
        JpaRepository<? extends Product, Integer> current_repository = repositories.get(productType);
        return current_repository.findById(id).orElse(null);
    }
}
