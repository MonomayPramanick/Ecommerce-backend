package com.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private int cartItemId;
    private int productId;
    private String productName;
    private double productPrice;
    private int quantity;
    private double subTotal; 
}
