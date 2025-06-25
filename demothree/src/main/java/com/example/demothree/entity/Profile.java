package com.example.demothree.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "tb_profile")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 45)
    private String fullName;
    @Column(length = 50)
    private String email;
    @Column(length = 15)
    private String phone;
    private String address;
}
