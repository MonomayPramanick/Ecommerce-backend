package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
    
    // delete all items belonging to a cart
    void deleteByCart_CartId(int cartId);

    // find all items belonging to a cart
    List<CartItem> findByCart_CartId(int cartId);
}
