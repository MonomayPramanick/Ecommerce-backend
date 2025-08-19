package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.payload.CartItemDto;
import com.backend.service.CartItemService;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

   
    @PostMapping("/{cartId}/product/{productId}")
    public ResponseEntity<CartItemDto> addItemToCart(
            @PathVariable int cartId,
            @PathVariable int productId,
            @RequestParam int quantity) {
        CartItemDto cartItemDto = cartItemService.addItemToCart(cartId, productId, quantity);
        return new ResponseEntity<CartItemDto>(cartItemDto,HttpStatus.OK);
    }

   
    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItemDto> updateCartItemQuantity(
            @PathVariable int cartItemId,
            @RequestParam int quantity) {
        CartItemDto cartItemDto = cartItemService.updateCartItemQuantity(cartItemId, quantity);
        return new ResponseEntity<CartItemDto>(cartItemDto,HttpStatus.OK);
    }


    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<String> removeItemFromCart(@PathVariable int cartItemId) {
        cartItemService.removeItemFromCart(cartItemId);
        return new ResponseEntity<String>("Cart item removed successfully",HttpStatus.OK);
    }

    
    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<CartItemDto>> getCartItems(@PathVariable int cartId) {
        List<CartItemDto> items = cartItemService.getCartItems(cartId);
        return new ResponseEntity<List<CartItemDto>>(items,HttpStatus.OK);
    }
}
