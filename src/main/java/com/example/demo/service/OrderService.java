package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.BookDAO;
import com.example.demo.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public Order submitOrder() {
        User user = userService.getCurrentUser();
        Cart cart = user.getCart();
        Order order = new Order();
        order.setDate(new Date());
        order.setStatus(OrderStatus.SUBMITTED);
        order.setUser(user);
        order.setPrice(cart.getTotalPrice());
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            cartItem.getBook().setQuantity(cartItem.getBook().getQuantity() - cartItem.getQuantity());
            order.getItems().add(orderItem);
        }
        cart.getItems().clear();
        cartService.saveCart(cart);
        return orderRepository.save(order);
    }

    @Transactional
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(()
                -> new RuntimeException("Order not found"));
    }
    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public void saveOrUpdate(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
