package com.backend.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private int cartId;
    private int userId;
    private List<CartItemDto> items;
    private double totalPrice; 
}
