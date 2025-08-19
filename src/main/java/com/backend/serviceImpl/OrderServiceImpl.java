package com.backend.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Cart;
import com.backend.model.Order;
import com.backend.model.OrderStatus;
import com.backend.model.User;
import com.backend.payload.OrderDto;
import com.backend.repositories.CartRepo;
import com.backend.repositories.OrderRepo;
import com.backend.repositories.UserRepo;
import com.backend.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private CartRepo cartrepo;
	
	@Autowired
	private OrderRepo orderrepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public OrderDto createOrder(int userId, int cartId, BigDecimal totalPrice) {
		User user=this.userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," UserId ",userId));
		Cart cart = this.cartrepo.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "Id", cartId));
		
		Order order=new Order();
		order.setUser(user);
		order.setCart(cart);
		order.setTotalPrice(totalPrice);
		order.setStatus("PENDING");

        Order savedOrder = orderrepo.save(order);

        return this.modelmapper.map(savedOrder, OrderDto.class);
        		
		
		
	}

	@Override
	public OrderDto getOrderById(int orderId) {
		Order order=this.orderrepo.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order"," OrderId ",orderId));
		return this.modelmapper.map(order, OrderDto.class);
	}

	@Override
	public List<OrderDto> getOrdersByUser(int userId) {
		User user=this.userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," UserId ",userId));
		return orderrepo.findByUser_Id(user.getId()).stream().map(order->this.modelmapper.map(order, OrderDto.class))
				.toList();	
	}

	@Override
	public OrderDto updateOrderStatus(int orderId, String status) {
		Order order=this.orderrepo.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order"," OrderId ",orderId));
		order.setStatus(status);
		return this.modelmapper.map(order, OrderDto.class);
	}

	@Override
	public void deleteOrder(int orderId) {
		Order order=this.orderrepo.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order"," OrderId ",orderId));
		this.orderrepo.delete(order);


	}

}
