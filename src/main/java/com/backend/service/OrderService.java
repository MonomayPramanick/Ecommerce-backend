package com.backend.service;

import java.math.BigDecimal;
import java.util.List;

import com.backend.model.OrderStatus;
import com.backend.payload.OrderDto;

public interface OrderService {
    OrderDto createOrder(int userId, int cartId, BigDecimal totalPrice);
    OrderDto getOrderById(int orderId);
    List<OrderDto> getOrdersByUser(int userId);
    OrderDto updateOrderStatus(int orderId,String status);
    void deleteOrder(int orderId);
}
