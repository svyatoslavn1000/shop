package com.geekbrains.decembermarket.controllers;

import com.geekbrains.decembermarket.beans.Cart;
import com.geekbrains.decembermarket.entities.Category;
import com.geekbrains.decembermarket.entities.Order;
import com.geekbrains.decembermarket.entities.Product;
import com.geekbrains.decembermarket.entities.User;
import com.geekbrains.decembermarket.services.CategoryService;
import com.geekbrains.decembermarket.services.OrderService;
import com.geekbrains.decembermarket.services.ProductService;
import com.geekbrains.decembermarket.services.UserService;
import com.geekbrains.decembermarket.utils.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final Cart cart;

    public OrderController(UserService userService, OrderService orderService, ProductService productService, CategoryService categoryService, Cart cart) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.cart = cart;
    }

    @GetMapping("/info")
    public String showOrderInfo(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findByPhone(principal.getName());
            model.addAttribute("def_phone", user.getPhone());
        }
//        model.addAttribute("cart", cart);
        return "order_info_before_confirmation";
    }

    @PostMapping("/create")
    public String createOrder(Principal principal, Model model, @RequestParam(name = "address") String address, @RequestParam("phone_number") String phone) {
        User user = null;
        if (principal != null) {
            user = userService.findByPhone(principal.getName());
        } else {
            user = userService.getAnonymousUser();
        }
        Order order = new Order(user, cart, address, phone);
        order = orderService.save(order);
        model.addAttribute("order_id_str", String.format("%05d", order.getId()));
        model.addAttribute("order_id", order.getId());
        return "order_confirmation";
    }

    @GetMapping("/history")
    public String showHistory(Model model, Principal principal) {
        User user = userService.findByPhone(principal.getName());
        model.addAttribute("username", user.getFullName());
        model.addAttribute("orders", user.getOrders());
        return "orders_history";
    }

    @GetMapping("/all")
    public String index(Model model, @RequestParam Map<String, String> params) {
        int pageIndex = 0;
        if (params.containsKey("p")) {
            pageIndex = Integer.parseInt(params.get("p")) - 1;
        }
        Pageable pageRequest = PageRequest.of(pageIndex, 10);
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> page = productService.findAll(productFilter.getSpec(), pageRequest);

        List<Category> categories = categoryService.getAll();
        model.addAttribute("filtersDef", productFilter.getFilterDefinition());
        model.addAttribute("categories", categories);
        model.addAttribute("page", page);

        return "index";
    }
}
