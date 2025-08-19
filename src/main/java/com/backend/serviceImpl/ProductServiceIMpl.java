package com.backend.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Product;
import com.backend.payload.ProductDto;
import com.backend.repositories.ProductRepo;
import com.backend.service.ProductService;


@Service
public class ProductServiceIMpl implements ProductService {
	@Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;
	
	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product product=this.modelMapper.map(productDto, Product.class);
		Product save=this.productRepo.save(product);
		return this.modelMapper.map(save, ProductDto.class);
	}

	@Override
	public ProductDto getProductById(int id) {
		Product prod=this.productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product"," ProductId ",id));
		return this.modelMapper.map(prod, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<ProductDto>allproduct=this.productRepo.findAll()
											.stream()
											.map((prod)->this.modelMapper.map(prod,ProductDto.class)).toList();
		return allproduct;
	}

	@Override
	public ProductDto updateProduct(int id, ProductDto productDto) {
		Product prod=this.productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product"," ProductId ",id));
		prod.setName(productDto.getName());
		prod.setPrice(productDto.getPrice());
		prod.setDescription(productDto.getDescription());
		prod.setStockQuantity(productDto.getStockQuantity());
		Product updatedProduct = this.productRepo.save(prod);
		return this.modelMapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public void deleteProduct(int id) {
		Product prod=this.productRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Product"," ProductId ",id));
		this.productRepo.delete(prod);
		
	}

	@Override
	public List<ProductDto> searchProductsByName(String name) {
		List<ProductDto>productbyname=this.productRepo.searchProductsByName(name)
										.stream()
										.map((prod)->this.modelMapper.map(prod,ProductDto.class)).toList();
		return productbyname;
	}

	@Override
	public List<ProductDto> filterProductsByPrice(double minPrice, double maxPrice) {
		List<ProductDto>productbyprice=this.productRepo.filterProductsByPrice(minPrice, maxPrice)
				.stream()
				.map((prod)->this.modelMapper.map(prod,ProductDto.class)).toList();
		return productbyprice;
	}

}
