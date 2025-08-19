package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.payload.CartDto;
import com.backend.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    
    @PostMapping("/user/{userId}")
    public ResponseEntity<CartDto> createCartForUser(@PathVariable int userId) {
        CartDto cartDto = cartService.createCartForUser(userId);
        return ResponseEntity.ok(cartDto);
    }

  
    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable int userId) {
        CartDto cartDto = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartDto);
    }

   
    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<String> clearCart(@PathVariable int cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok("Cart cleared successfully");
    }

   
    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable int cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok("Cart deleted successfully");
    }
}
