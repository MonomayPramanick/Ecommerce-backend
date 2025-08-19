package com.backend.service;

import java.util.List;

import com.backend.payload.ProductDto;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto getProductById(int id);
    List<ProductDto> getAllProducts();
    ProductDto updateProduct(int id, ProductDto productDto);
    void deleteProduct(int id);

    List<ProductDto> searchProductsByName(String name);
  //  List<ProductDto> getProductsByCategory(int categoryId);
    List<ProductDto> filterProductsByPrice(double minPrice, double maxPrice);
}
