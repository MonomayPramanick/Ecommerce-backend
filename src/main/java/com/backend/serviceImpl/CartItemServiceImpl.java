package com.backend.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Cart;
import com.backend.model.CartItem;
import com.backend.model.Product;
import com.backend.payload.CartItemDto;
import com.backend.repositories.CartItemRepo;
import com.backend.repositories.CartRepo;
import com.backend.repositories.ProductRepo;
import com.backend.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	 	@Autowired
	    private CartItemRepo cartItemRepo;

	    @Autowired
	    private CartRepo cartRepo;

	    @Autowired
	    private ProductRepo productRepo;

	    @Autowired
	    private ModelMapper modelMapper;
	
	@Override
	public CartItemDto addItemToCart(int cartId, int productId, int quantity) {
		Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        // Create new cart item
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        CartItem saved = cartItemRepo.save(cartItem);
        return modelMapper.map(saved, CartItemDto.class);
	}

	@Override
	public CartItemDto updateCartItemQuantity(int cartItemId, int quantity) {
		 CartItem cartItem = cartItemRepo.findById(cartItemId)
	                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "cartItemId", cartItemId));

	        cartItem.setQuantity(quantity);
	        CartItem updated = cartItemRepo.save(cartItem);
	        return modelMapper.map(updated, CartItemDto.class);
	}

	@Override
	public void removeItemFromCart(int cartItemId) {
		CartItem cartItem = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", "cartItemId", cartItemId));

        cartItemRepo.delete(cartItem);
		
	}

	@Override
	public List<CartItemDto> getCartItems(int cartId) {
		List<CartItem> items = cartItemRepo.findByCart_CartId(cartId);
        return items.stream()
                .map(item -> modelMapper.map(item, CartItemDto.class))
                .toList();
	}

}
