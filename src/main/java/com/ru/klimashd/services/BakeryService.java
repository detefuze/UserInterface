package com.ru.klimashd.services;

import com.ru.klimashd.entities.Bakery;
import com.ru.klimashd.repositories.BakeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BakeryService {
    private final BakeryRepository bakeryRepository;

    @Autowired
    public BakeryService(BakeryRepository bakeryRepository) {
        this.bakeryRepository = bakeryRepository;
    }

    public List<Bakery> getAllBakeryProducts() {
        return bakeryRepository.findAll();
    }
}
