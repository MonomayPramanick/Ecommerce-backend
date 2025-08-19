package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private double price;


    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;
    
    private String imageName;

//    // Assuming you have a Category entity
//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;

 
}
