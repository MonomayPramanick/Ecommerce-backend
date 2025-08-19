package com.backend.serviceImpl;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Order;
import com.backend.model.Payment;
import com.backend.model.PaymentStatus;
import com.backend.model.User;
import com.backend.payload.PaymentDto;
import com.backend.repositories.OrderRepo;
import com.backend.repositories.PaymentRepo;
import com.backend.repositories.UserRepo;
import com.backend.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepo paymentrepo;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private OrderRepo orderrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public PaymentDto createPayment(int userId, int orderId,BigDecimal totalprice) {
		User user=this.userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," UserId ",userId));
		Order order=this.orderrepo.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order"," OrderId ",orderId));
		Payment payment=new Payment();
		payment.setOrder(order);
		payment.setUser(user);
		payment.setTotalprice(totalprice);
		Payment saved=this.paymentrepo.save(payment);
		
		return this.modelmapper.map(saved, PaymentDto.class);
		
	}

	@Override
	public PaymentDto updateStatus(int paymentId, String status) {
	    Payment payment = this.paymentrepo.findById(paymentId)
	        .orElseThrow(() -> new ResourceNotFoundException("Payment", "PaymentId", paymentId));

	    payment.setStatus(status);  

	    Payment updatedPayment = paymentrepo.save(payment); 

	    return this.modelmapper.map(updatedPayment, PaymentDto.class); 
	}

	@Override
	public PaymentDto getPaymentById(int paymentId) {
		Payment payment = this.paymentrepo.findById(paymentId)
		        .orElseThrow(() -> new ResourceNotFoundException("Payment", "PaymentId", paymentId));
	    return this.modelmapper.map(payment, PaymentDto.class); 

	}


}
