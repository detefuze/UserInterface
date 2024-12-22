package com.ru.klimashd.controllers;

import com.ru.klimashd.entities.*;
import com.ru.klimashd.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/main_menu")
public class UserInterfaceController {

    private final VegetablesService vegetablesService;
    private final BakeryService bakeryService;
    private final FruitsService fruitsService;
    private final DairyService dairyService;
    private final BasketService basketService;

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
        return "food-list";
    }

    @GetMapping("/vegetables")
    public String vegetablesMenu(Model model) {
        List<Vegetables> vegetablesList = vegetablesService.getAllVegetables();
        model.addAttribute("vegetables", vegetablesList);
        return "vegetables";
    }

    @GetMapping("/dairy")
    public String dairyMenu(Model model) {
        List<Dairy> dairyList = dairyService.getAllDairyProducts();
        model.addAttribute("dairy_products", dairyList);
        return "dairy";
    }

    @GetMapping("/fruits")
    public String fruitsMenu(Model model) {
        List<Fruits> fruitsList = fruitsService.getAllFruits();
        model.addAttribute("fruits", fruitsList);
        return "fruits";
    }

    @GetMapping("/bakery")
    public String bakeryMenu(Model model) {
        List<Bakery> bakeryList = bakeryService.getAllBakeryProducts();
        model.addAttribute("bakery_products", bakeryList);
        return "bakery";
    }
}
