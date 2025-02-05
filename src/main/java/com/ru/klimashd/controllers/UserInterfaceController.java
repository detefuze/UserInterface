package com.ru.klimashd.controllers;

import com.ru.klimashd.dto.CustomerDTO;
import com.ru.klimashd.entities.*;
import com.ru.klimashd.services.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Integer customer_balance;

    @Autowired
    public UserInterfaceController(VegetablesService vegetablesService,
                                   BakeryService bakeryService,
                                   FruitsService fruitsService,
                                   DairyService dairyService,
                                   BasketService basketService) {
        this.vegetablesService = vegetablesService;
        this.bakeryService = bakeryService;
        this.fruitsService = fruitsService;
        this.dairyService = dairyService;
        this.basketService = basketService;
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
                        vegetable.get().getPrice()
                ));
        }
        return "productType";
    }
}
