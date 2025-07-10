package com.ru.klimashd.controllers;

import com.ru.klimashd.dto.BasketDTO;
import com.ru.klimashd.dto.CustomerDTO;
import com.ru.klimashd.entities.*;
import com.ru.klimashd.enums.ProductType;
import com.ru.klimashd.mapper.MapperToBasketDTO;
import com.ru.klimashd.services.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/main_menu")
@RequiredArgsConstructor
public class UserInterfaceController {

    // Логгер
    private final Logger logger = LoggerFactory.getLogger(UserInterfaceController.class);

    // Сервисы продуктов и корзины
    private final VegetablesService vegetablesService;
    private final BakeryService bakeryService;
    private final FruitsService fruitsService;
    private final DairyService dairyService;
    private final BasketService basketService;

    // Сервис оформления заказа
    private final FoodOrderService foodOrderService;

    // Общий сервис для продуктов
    private final ProductService productService;

    // Сервис обработки баланса пользователя
    private final BalanceService balanceService;

    private final MapperToBasketDTO mapperToBasketDTO;
    private Integer customer_balance;
    private Integer totalSum;

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
    public String mainMenu(@RequestBody CustomerDTO customerDTO, Model model) {
        customer_balance = customerDTO.getBalance();
        return "food-list";
    }

    // Добавление в корзину
    @PostMapping("/{productType}/add_to_cart")
    public String addProductToBasket(
            @PathVariable String productType,
            @RequestParam Integer id_product,
            @RequestParam int added_amount
            ) {
        Class<? extends Product> productClass = ProductType.fromString(productType);

        Product product = productService.getProductByIdAndProductClass(id_product, productClass);
        Basket basket = new Basket(product.getName(), added_amount, product.getPrice());
        basket.setProduct(product); // Устанавливаем связь с product
        basketService.addNewBasketPosition(basket, productClass);
        return "redirect:/main_menu/{productType}";
    }

    // Отправка заказа
    @PostMapping("/sendOrder")
    public String sendOrder(@CookieValue(value = "customerToken") String token,
                            HttpServletResponse response) {
        if (token == null) {
            return "redirect:http://localhost:8082/main_menu/authentication_api/authentication";
        }

        SecretKey key = Keys.hmacShaKeyFor("1877-2974-2794-3294-8490-4829-7594".getBytes(StandardCharsets.UTF_8));
        try {
            // Парсим JWT-токен из куки
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            // Получаем ID пользователя из токена
            int customerId = Integer.parseInt(claims.getSubject());
            System.out.println("Customer ID from token: " + customerId);

            // Проверка баланса пользователя с учетом суммы заказа
            if (customer_balance < totalSum) {
                return "redirect:/main_menu";
            }

            // Списываем сумму и создаём заказ
            customer_balance -= totalSum;
            List<Basket> basket = basketService.getAllOrders();
            List<BasketDTO> responseDTO = mapperToBasketDTO.mapListToBasketDTO(customerId, basket);
            foodOrderService.createOrder(responseDTO);

            return "redirect:/main_menu";
        } catch (Exception e) {
            return "redirect:/main_menu";
        }

    }

    // Получение заказа на обработку
    @PostMapping("/getOrder")
    public String getOrder(@RequestBody List<BasketDTO> basketDTOList) {
        balanceService.updateCustomerBalance(basketDTOList);
        productService.updateProductsAmount(basketDTOList);
        basketService.freeBasket();
        return "redirect:/main_menu";
    }
}
