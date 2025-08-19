package com.backend.service;

import java.util.List;

import com.backend.payload.CartItemDto;

public interface CartItemService {
    CartItemDto addItemToCart(int cartId, int productId, int quantity); 
    CartItemDto updateCartItemQuantity(int cartItemId, int quantity);   
    void removeItemFromCart(int cartItemId);                           
    List<CartItemDto> getCartItems(int cartId);                         
}
