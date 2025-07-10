package com.ru.klimashd.services;

import com.ru.klimashd.dto.BasketDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BalanceService {

    private final Logger logger = LoggerFactory.getLogger(BalanceService.class);
    private final RestTemplate restTemplate;
    private final String urlCustomerService;

    public BalanceService(RestTemplate restTemplate, @Value("${url.customer.service}") String urlCustomerService) {
        this.restTemplate = restTemplate;
        this.urlCustomerService = urlCustomerService;
    }

    public int totalOrderSum(List<BasketDTO> order) {
        int total = 0;
        for (BasketDTO pos : order) total += pos.getPrice();
        return total;
    }

    public void updateCustomerBalance(List<BasketDTO> basketDTOList) {
        if (basketDTOList == null || basketDTOList.isEmpty()) {
            throw new IllegalArgumentException("BasketDTO list cannot be empty");
        }

        int id_customer = basketDTOList.get(0).getCustomer_id();
        int totalSum = totalOrderSum(basketDTOList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Integer> customer_response = new HashMap<>();
        customer_response.put("customerId", id_customer);
        customer_response.put("totalSum", totalSum);
        HttpEntity<Map<String, Integer>> customerEntityResponse = new HttpEntity<>(customer_response, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
                    urlCustomerService,
                    customerEntityResponse,
                    String.class
            );
    }
}