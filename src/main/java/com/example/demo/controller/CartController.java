package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/add/{bookId}/{quantity}")
    public String addToCart(@RequestParam(value = "message", required = false) String message,
                            @PathVariable int bookId, @PathVariable int quantity) {
        cartService.addToCart(bookId, quantity);
        if (message != null) {
            return "redirect:/cart?message=" + "Nie można dodać książki do koszyka, brak wystarczającej ilości na stanie!";
        } //TODO add message to cart
        return "redirect:/cart";
    }

    @GetMapping
    public String getCart(Model model) {
        Cart cart = cartService.getCart();
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable int bookId) {
        cartService.removeFromCart(bookId);
        return "redirect:/cart";
    }
}
