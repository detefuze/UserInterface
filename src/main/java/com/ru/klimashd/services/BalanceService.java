package com.ru.klimashd.services;

import com.ru.klimashd.dto.BasketDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BalanceService {

    private final RestTemplate restTemplate;
    private final String url_customer_service;

    public BalanceService(RestTemplate restTemplate, @Value("${url_customer_service}") String url_customer_service) {
        this.restTemplate = restTemplate;
        this.url_customer_service = url_customer_service;
    }

    public int totalOrderSum(List<BasketDTO> order) {
        int total = 0;
        for (BasketDTO pos : order) total += pos.getPrice()*pos.getOrder_amount();
        return total;
    }

    public void updateCustomerBalance(List<BasketDTO> basketDTOList) {
        int id_customer = basketDTOList.get(0).getCustomer_id();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<Integer, Integer> customer_response = new HashMap<>(id_customer, totalOrderSum(basketDTOList));
        HttpEntity<Map<Integer, Integer>> customerEntityResponse = new HttpEntity<>(customer_response, headers);

        ResponseEntity<String> customerResponse = restTemplate.postForEntity(url_customer_service,
                customerEntityResponse,
                String.class);
    }
}
