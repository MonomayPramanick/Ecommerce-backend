package com.backend.service;

import java.math.BigDecimal;

import com.backend.model.PaymentStatus;
import com.backend.payload.PaymentDto;

public interface PaymentService {

	PaymentDto createPayment(int userId,int orderId,BigDecimal totalprice);
	
	PaymentDto updateStatus(int paymentId,String status);

	PaymentDto getPaymentById(int paymentId);
}
