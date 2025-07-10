package com.ru.klimashd.services;

import com.ru.klimashd.dto.BasketDTO;
import com.ru.klimashd.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodOrderService {

    private final RestTemplate restTemplate;

    @Autowired
    public FoodOrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createOrder(List<BasketDTO> response) {
        String uiServiceUrl = "http://localhost:8084/order";

        // TODO сделать ссылку на микросервис обработки баланса

        ResponseEntity<String> authenticationResponse = restTemplate.postForEntity(uiServiceUrl,
                response,
                String.class);
    }
}
