package com.example.training.handsontwo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private Double balance;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CookieOrder> cookieOrderList;
}
