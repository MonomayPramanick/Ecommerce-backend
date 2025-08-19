package com.backend.controller;

import com.backend.model.OrderStatus;
import com.backend.payload.OrderDto;
import com.backend.service.CartService;
import com.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    // ✅ Create a new order
    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(
            @RequestParam int userId,
            @RequestParam int cartId,
            @RequestParam BigDecimal totalPrice) {

        OrderDto order = orderService.createOrder(userId, cartId, totalPrice);
        cartService.clearCart(cartId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable int orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUser(@PathVariable int userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }


    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @PathVariable int orderId,
            @RequestParam String status) {

        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully");
    }
}
