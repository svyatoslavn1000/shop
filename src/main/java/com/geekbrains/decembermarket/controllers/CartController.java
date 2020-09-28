package com.geekbrains.decembermarket.controllers;

import com.geekbrains.decembermarket.beans.Cart;
import com.geekbrains.decembermarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
public class CartController {
    private ProductService productService;
    private Cart cart;

    @Autowired
    public CartController(ProductService productService, Cart cart) {
        this.productService = productService;
        this.cart = cart;
    }

    @GetMapping("/add/{id}")
    public void addProductToCartById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        cart.add(productService.findById(id));
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/decrement/{id}")
    public void decrementProductToCartById(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        cart.decrement(productService.findById(id));
        response.sendRedirect(request.getHeader("referer"));
    }

    @GetMapping("/remove/{id}")
    public String removeProductFromCartById(@PathVariable Long id) {
        cart.removeByProductId(id);
        return "redirect:/cart";
    }

    @GetMapping
    public String showCart(Model model) {
//        можно убрать, если пометить в темплейтах корзину как бин @cart:
//        model.addAttribute("cart", cart);
        return "cart_page";
    }
}