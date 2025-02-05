package com.ru.klimashd.services;

import com.ru.klimashd.entities.Vegetables;
import com.ru.klimashd.repositories.VegetablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VegetablesService {

    private final VegetablesRepository vegetablesRepository;

    @Autowired
    public VegetablesService(VegetablesRepository vegetablesRepository) {
        this.vegetablesRepository = vegetablesRepository;
    }

    public List<Vegetables> getAllVegetables() {
        return vegetablesRepository.findAll();
    }

    public Optional<Vegetables> getVegetableById(int id) { return vegetablesRepository.findVegetablesById(id);}
}
