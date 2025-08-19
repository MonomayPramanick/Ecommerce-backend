package com.backend.controller;

import com.backend.exception.ResourceNotFoundException;
import com.backend.model.Order;
import com.backend.model.PaymentStatus;
import com.backend.payload.*;
import com.backend.payload.*;
import com.backend.service.OrderService;
import com.backend.service.PaymentService;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{userId}/user/{orderId}/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private OrderService orderService;


    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(
            @PathVariable int userId,
            @PathVariable int orderId,
            @RequestParam BigDecimal totalPrice) {

    	PaymentDto createdPayment = paymentService.createPayment(userId, orderId, totalPrice);

        // update order status to PLACED
        orderService.updateOrderStatus(orderId, "PLACED");

        return ResponseEntity.ok(createdPayment);
    }

    // ✅ Update payment status
    @PutMapping("/{paymentId}/status")
    public ResponseEntity<PaymentDto> updatePaymentStatus(
            @PathVariable int userId,
            @PathVariable int orderId,
            @PathVariable int paymentId,
            @RequestParam String status) {

        PaymentDto updatedPayment = paymentService.updateStatus(paymentId, status);
        return ResponseEntity.ok(updatedPayment);
    }

    // ✅ Get payment by ID
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDto> getPaymentById(
            @PathVariable int userId,
            @PathVariable int orderId,
            @PathVariable int paymentId) {

    	
        PaymentDto payment = paymentService.getPaymentById(paymentId);
        return ResponseEntity.ok(payment);
    }
}
