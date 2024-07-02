package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();
    @OneToOne(mappedBy = "cart")
    private User user;

    public void addItem(Book book, int quantity){
        for (CartItem item : items){
            if (item.getBook().getId() == book.getId()){
                if(item.getBook().getQuantity() < item.getQuantity() + quantity){
                    item.setQuantity(item.getBook().getQuantity());
                    //throw new RuntimeException("Not enough books in stock");
                } else {
                    item.setQuantity(item.getQuantity() + quantity);
                }
                return;
            }
        }
        CartItem item = new CartItem();
        item.setBook(book);
        item.setQuantity(quantity);
        item.setCart(this);
        items.add(item);
    }

    public void removeItem(Book book){
        int id = book.getId();
        for (CartItem item : items){
            if (item.getBook().getId() == id){
                items.remove(item);
                item.setCart(null);
                return;
            }
        }
    }

    public BigDecimal getTotalPrice() {
        BigDecimal sum = BigDecimal.ZERO;
        for (CartItem item : items) {
            sum = sum.add(item.getBook().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return sum;
    }
}