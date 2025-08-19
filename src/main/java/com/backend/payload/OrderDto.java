package com.backend.payload;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.backend.model.OrderStatus;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private int orderId;
    private int userId;       
    private int cartId;       
    private BigDecimal totalPrice;
    private Timestamp createdAt;
    private String status;
}
