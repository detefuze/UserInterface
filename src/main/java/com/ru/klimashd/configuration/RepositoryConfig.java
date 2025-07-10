package com.ru.klimashd.configuration;

import com.ru.klimashd.entities.*;
import com.ru.klimashd.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RepositoryConfig {

    @Bean
    public Map<Class<? extends Product>, ProductBaseRepository<?>> productRepositories(
            VegetablesRepository vegetablesRepository,
            FruitsRepository fruitsRepository,
            DairyRepository dairyRepository,
            BakeryRepository bakeryRepository
    ) {
        Map<Class<? extends Product>, ProductBaseRepository<?>> repositories = new HashMap<>();
        repositories.put(Vegetables.class, vegetablesRepository);
        repositories.put(Fruits.class, fruitsRepository);
        repositories.put(Dairy.class, dairyRepository);
        repositories.put(Bakery.class, bakeryRepository);
        return repositories;
    }

    @Bean
    public Map<Class<? extends Product>, JpaRepository<? extends Product, Integer>> jpaProductRepositories(
            BakeryRepository bakeryRepository,
            DairyRepository dairyRepository,
            FruitsRepository fruitsRepository,
            VegetablesRepository vegetablesRepository
    )
    {
        Map<Class<? extends Product>, JpaRepository<? extends Product, Integer>> jpaRepositories = new HashMap<>();
        jpaRepositories.put(Bakery.class, bakeryRepository);
        jpaRepositories.put(Dairy.class, dairyRepository);
        jpaRepositories.put(Fruits.class, fruitsRepository);
        jpaRepositories.put(Vegetables.class, vegetablesRepository);

        return Collections.unmodifiableMap(jpaRepositories);
    }
}
