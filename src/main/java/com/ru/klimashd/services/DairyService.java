package com.ru.klimashd.services;

import com.ru.klimashd.entities.Bakery;
import com.ru.klimashd.entities.Dairy;
import com.ru.klimashd.repositories.DairyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DairyService {

    private final DairyRepository dairyRepository;

    @Autowired
    public DairyService(DairyRepository dairyRepository) {
        this.dairyRepository = dairyRepository;
    }

    public List<Dairy> getAllDairyProducts() {
        return dairyRepository.findAll();
    }

    public Dairy getDairyById(int id) { return dairyRepository.getDairyById(id);}
}
