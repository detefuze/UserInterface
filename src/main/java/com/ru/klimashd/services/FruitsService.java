package com.ru.klimashd.services;

import com.ru.klimashd.entities.Fruits;
import com.ru.klimashd.repositories.FruitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FruitsService {

    private final FruitsRepository fruitsRepository;

    @Autowired
    public FruitsService(FruitsRepository fruitsRepository) {
        this.fruitsRepository = fruitsRepository;
    }

    public List<Fruits> getAllFruits() {
        return fruitsRepository.findAll();
    }

    public Optional<Fruits> getFruitsById(int id) { return fruitsRepository.findFruitsById(id);}
}
