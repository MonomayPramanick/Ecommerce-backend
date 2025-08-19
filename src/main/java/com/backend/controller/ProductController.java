package com.backend.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.backend.payload.ProductDto;
import com.backend.service.FileService;
import com.backend.service.ProductService;
import com.blog.boot.payloads.PostDto;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private FileService fileservice;
    
    @Value("${project.image}")
	private String path;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto created = productService.createProduct(productDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }


    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable int id, @RequestBody ProductDto productDto) {
        ProductDto updated = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully with ID: " + id);
    }

 
    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProductsByName(@RequestParam String name) {
        List<ProductDto> products = productService.searchProductsByName(name);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/filter")
    public ResponseEntity<List<ProductDto>> filterProductsByPrice(
            @RequestParam double minPrice,
            @RequestParam  double maxPrice) {
        List<ProductDto> products = productService.filterProductsByPrice(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
    
    
	
	@PostMapping("/image/upload/{productId}")
	public ResponseEntity<ProductDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable("productId") Integer productId) throws IOException{
		ProductDto productdto=this.productService.getProductById(productId);
		String filename=this.fileservice.uploadImage(path, image);
		 productdto.setImageName(filename);
		 ProductDto updateproduct=this.productService.updateProduct( productId, productdto);
		return new ResponseEntity<ProductDto>(updateproduct,HttpStatus.OK);
	}
	
	@GetMapping(value = "/image/{imageName}", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
	        @PathVariable("imageName") String imageName,
	        HttpServletResponse response) throws IOException {

	    // Get the image as InputStream
	    InputStream resource = this.fileservice.getResource(path, imageName);

	    // Set content type
	    response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);

	    // Copy stream to response output
	    org.springframework.util.StreamUtils.copy(resource, response.getOutputStream());
	}

	
	
	
}
