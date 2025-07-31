package com.example.demofive.demofive.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String customerName;
    private String customerAddress;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<BankAccount> bankAccountList;
}
