package com.example.training.handsontwo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CookieOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String orderNumber;

    @CreationTimestamp
    private LocalDateTime orderDate;
    private double totalAmount;

    // Additional fields and methods can be added as needed
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;
}
