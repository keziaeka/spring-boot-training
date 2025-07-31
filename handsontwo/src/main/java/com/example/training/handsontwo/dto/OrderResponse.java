package com.example.training.handsontwo.dto;

import com.example.training.handsontwo.entity.Customer;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponse {
    private String orderNumber;
    private String orderDate;
    private double totalAmount;
    private String customerName;
    private double currentBalance;
}
