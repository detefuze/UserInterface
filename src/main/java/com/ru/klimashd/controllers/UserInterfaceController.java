package com.ru.klimashd.controllers;

import com.ru.klimashd.dto.BasketDTO;
import com.ru.klimashd.dto.CustomerDTO;
import com.ru.klimashd.entities.*;
import com.ru.klimashd.mapper.MapperToBasketDTO;
import com.ru.klimashd.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/main_menu")
public class UserInterfaceController {

    private final VegetablesService vegetablesService;
    private final BakeryService bakeryService;
    private final FruitsService fruitsService;
    private final DairyService dairyService;
    private final BasketService basketService;
    private final FoodOrderService foodOrderService;
    private final MapperToBasketDTO mapperToBasketDTO;
    private Integer customer_balance;

    @Autowired
    public UserInterfaceController(VegetablesService vegetablesService,
                                   BakeryService bakeryService,
                                   FruitsService fruitsService,
                                   DairyService dairyService,
                                   BasketService basketService,
                                   FoodOrderService foodOrderService,
                                   MapperToBasketDTO mapperToBasketDTO) {
        this.vegetablesService = vegetablesService;
        this.bakeryService = bakeryService;
        this.fruitsService = fruitsService;
        this.dairyService = dairyService;
        this.basketService = basketService;
        this.foodOrderService = foodOrderService;
        this.mapperToBasketDTO = mapperToBasketDTO;
    }

    @GetMapping("")
    public String mainMenu(Model model) {
        List<Basket> basketList = basketService.getAllOrders();
        model.addAttribute("products", basketList);
        model.addAttribute("balance", customer_balance);
        return "food-list";
    }

    @GetMapping("/vegetables")
    public String vegetablesMenu(Model model) {
        List<Vegetables> vegetablesList = vegetablesService.getAllVegetables();
        model.addAttribute("vegetables", vegetablesList);
        model.addAttribute("balance", customer_balance);
        return "vegetables";
    }

    @GetMapping("/dairy")
    public String dairyMenu(Model model) {
        List<Dairy> dairyList = dairyService.getAllDairyProducts();
        model.addAttribute("dairy_products", dairyList);
        model.addAttribute("balance", customer_balance);
        return "dairy";
    }

    @GetMapping("/fruits")
    public String fruitsMenu(Model model) {
        List<Fruits> fruitsList = fruitsService.getAllFruits();
        model.addAttribute("fruits", fruitsList);
        model.addAttribute("balance", customer_balance);
        return "fruits";
    }

    @GetMapping("/bakery")
    public String bakeryMenu(Model model) {
        List<Bakery> bakeryList = bakeryService.getAllBakeryProducts();
        model.addAttribute("bakery_products", bakeryList);
        model.addAttribute("balance", customer_balance);
        return "bakery";
    }

    @PostMapping("")
    public String mainMenu(@RequestBody Optional<CustomerDTO> optionalCustomerDTO, Model model) {
        CustomerDTO customer = optionalCustomerDTO.get();
        customer_balance = customer.getBalance();
        return "food-list";
    }

    @PostMapping("/{productType}/add_to_cart")
    public String addProductToBasket(
            @PathVariable String productType,
            @RequestParam int id_product,
            @RequestParam int amount
            ) {
        switch (productType) {
            case "vegetables":
                Optional<Vegetables> vegetable = vegetablesService.getVegetableById(id_product);
                basketService.addNewOrder(new Basket(
                        productType,
                        vegetable.get().getName(),
                        amount,
                        vegetable.get().getPrice()*amount
                ));
                return "redirect:/main_menu/vegetables";
            case "bakery":
                Optional<Bakery> bakery = bakeryService.getBakeryById(id_product);
                basketService.addNewOrder(new Basket(
                        productType,
                        bakery.get().getName(),
                        amount,
                        bakery.get().getPrice()*amount
                ));
                return "redirect:/main_menu/bakery";
            case "dairy":
                Optional<Dairy> dairy = dairyService.getDairyById(id_product);
                basketService.addNewOrder(new Basket(
                        productType,
                        dairy.get().getName(),
                        amount,
                        dairy.get().getPrice()*amount
                ));
                return "redirect:/main_menu/dairy";
            case "fruits":
                Optional<Fruits> fruit = fruitsService.getFruitsById(id_product);
                basketService.addNewOrder(new Basket(
                        productType,
                        fruit.get().getName(),
                        amount,
                        fruit.get().getPrice()*amount
                ));
                return "redirect:/main_menu/fruits";
        }
        return "redirect:/main_menu";
    }

    @PostMapping("/order")
    public String sendOrder() {
        List<Basket> basket = basketService.getAllOrders();

        List<BasketDTO> basketDTOList = mapperToBasketDTO.mapListToBasketDTO(basket);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<BasketDTO>> response = new HttpEntity<>(basketDTOList, headers);

        foodOrderService.createOrder(response);

        return "redirect:/main_menu";
    }

}
