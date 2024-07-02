package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    OrderService orderService;

    @GetMapping("/admin/adminpanel")
    public String adminpanel() {
        return "admin";
    }

    @GetMapping("/admin/orders")
    public String orders(Model model) {
        model.addAttribute("order", this.orderService.getAll());
        return "orders";
    }

    @GetMapping("/admin/orders/{id}")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("orderStatusValues", OrderStatus.values());
        model.addAttribute("order", orderService.getOrder((long) id));
        return "order-form";
    }

    @PostMapping("/admin/orders/{id}")
    public String update(@PathVariable("id") Long orderId, @RequestParam("status") OrderStatus status) {
        Order order = orderService.getOrder(orderId);
        if (order != null) {
            order.setStatus(status);
            orderService.saveOrUpdate(order);
        }
        return "redirect:/admin/orders";
    }
}
