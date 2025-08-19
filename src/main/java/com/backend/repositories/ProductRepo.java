package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	 // Filter by price range
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> filterProductsByPrice(@Param("minPrice") double minPrice,
                                        @Param("maxPrice") double maxPrice);

    // Search by name (case insensitive)
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> searchProductsByName(@Param("name") String name);

}
