package com.training.demosix.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Double balance;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CookieOrder> cookieOrderList;
}
