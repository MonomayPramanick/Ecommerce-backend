package com.backend.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Cart;
import com.backend.model.User;
import com.backend.payload.CartDto;
import com.backend.repositories.CartItemRepo;
import com.backend.repositories.CartRepo;
import com.backend.repositories.ProductRepo;
import com.backend.repositories.UserRepo;
import com.backend.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	 	@Autowired
	    private CartRepo cartRepo;

	    @Autowired
	    private CartItemRepo cartItemRepo;

	    @Autowired
	    private UserRepo userRepo;

	    @Autowired
	    private ProductRepo productRepo;

	    @Autowired
	    private ModelMapper modelMapper;
	@Override
	public CartDto createCartForUser(int userId) {
		 User user = userRepo.findById(userId)
	                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

	        Cart cart = new Cart();
	        cart.setUser(user);
	        cart = cartRepo.save(cart);

	        return modelMapper.map(cart, CartDto.class);
	}

	@Override
	public CartDto getCartByUserId(int userId) {
		Cart cart = cartRepo.findByUser_Id(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "UserId", userId));

        return modelMapper.map(cart, CartDto.class);
	}

	@Override
	public void clearCart(int cartId) {
		cartItemRepo.deleteByCart_CartId(cartId);

	}

	@Override
	public void deleteCart(int cartId) {
		Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "Id", cartId));
        cartRepo.delete(cart);

	}

}
