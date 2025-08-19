package com.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private int id;              
    private String name;         
    private String description;  
    private double price;       
    private int stockQuantity;  
    
    private String imageName;
}
