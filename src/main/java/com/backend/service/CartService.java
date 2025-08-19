package com.backend.service;

import com.backend.payload.CartDto;

public interface CartService {
    CartDto createCartForUser(int userId);              
    CartDto getCartByUserId(int userId);              
    void clearCart(int cartId);                        
    void deleteCart(int cartId);                       
}
