package com.backend.payload;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.backend.model.PaymentStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDto {
    private int paymentId;
    private int orderId;
    private int userId;
    private BigDecimal totalprice;
    private String status;
    private Timestamp updatedAt;
}