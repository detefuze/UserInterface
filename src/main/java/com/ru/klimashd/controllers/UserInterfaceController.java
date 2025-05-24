package com.ru.klimashd.controllers;

import com.ru.klimashd.dto.BasketDTO;
import com.ru.klimashd.dto.CustomerDTO;
import com.ru.klimashd.enums.ProductType;
import com.ru.klimashd.mapper.MapperToBasketDTO;
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
    private final FoodOrderService foodOrderService;
    private final ProductService productService;
    private final MapperToBasketDTO mapperToBasketDTO;
    private Integer customer_balance;
    private Integer totalSum;

    @Autowired
    public UserInterfaceController(VegetablesService vegetablesService,
                                   BakeryService bakeryService,
                                   FruitsService fruitsService,
                                   DairyService dairyService,
                                   BasketService basketService,
                                   FoodOrderService foodOrderService,
                                   ProductService productService,
                                   MapperToBasketDTO mapperToBasketDTO) {
        this.vegetablesService = vegetablesService;
        this.bakeryService = bakeryService;
        this.fruitsService = fruitsService;
        this.dairyService = dairyService;
        this.basketService = basketService;
        this.foodOrderService = foodOrderService;
        this.productService = productService;
        this.mapperToBasketDTO = mapperToBasketDTO;
    }

    @GetMapping("")
    public String mainMenu(Model model) {
        List<Basket> basketList = basketService.getAllOrders();
        totalSum = basketList.stream().mapToInt(Basket::getPrice).sum(); // итоговая сумма
        model.addAttribute("products", basketList);
        model.addAttribute("balance", customer_balance);
        model.addAttribute("totalSum", totalSum);
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
            @RequestParam int added_amount
            ) {
        Class<? extends Product> productClass = ProductType.fromString(productType);
        Product product = productService.getProductById(id_product, productType);
        if (product == null) {
            return "redirect:/main_menu";
        }
        Basket basket = new Basket(product.getName(), added_amount, product.getPrice());
        basket.setProduct(product); // Устанавливаем связь с product
        basketService.addNewBasketPosition(basket, productClass);
        return "redirect:/main_menu/{productType}";
    }

    @PostMapping("/order")
    public String sendOrder() {
        if (customer_balance == null) return "redirect:http://localhost:8082/main_menu/authentication_api/authentication";
        if (customer_balance < totalSum) return "redirect:/main_menu";
        List<Basket> basket = basketService.getAllOrders();

        List<BasketDTO> response = mapperToBasketDTO.mapListToBasketDTO(basket);

        foodOrderService.createOrder(response);

        return "redirect:/main_menu";
    }

}
