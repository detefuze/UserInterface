package com.ru.klimashd.controllers;

import com.ru.klimashd.entities.Vegetables;
import com.ru.klimashd.services.VegetablesService;
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

    @Autowired
    public UserInterfaceController(VegetablesService vegetablesService) {
        this.vegetablesService = vegetablesService;
    }

    @GetMapping("")
    public String mainMenu(Model model) {
        return "food-list";
    }

    @GetMapping("/vegetables")
    public String vegetablesMenu(Model model) {
        List<Vegetables> vegetablesList = vegetablesService.getAllVegetables();
        model.addAttribute("vegetables", vegetablesList);
        return "vegetables";
    }

    @GetMapping("/dairy")
    public String dairyMenu() {
        return "dairy";
    }

    @GetMapping("/fruits")
    public String fruitsMenu() {
        return "fruits";
    }

    @GetMapping("/bakery")
    public String bakeryMenu() {
        return "bakery";
    }
}
