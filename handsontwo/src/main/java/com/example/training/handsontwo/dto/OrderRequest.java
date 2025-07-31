package com.example.training.handsontwo.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderRequest {

    private Integer customerId;
    private String orderNumber;
    private double totalAmount;
}
