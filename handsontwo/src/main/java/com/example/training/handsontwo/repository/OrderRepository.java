package com.example.training.handsontwo.repository;

import com.example.training.handsontwo.entity.CookieOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CookieOrder, Integer> {

    // Custom query methods can be defined here if needed
    // For example, to find orders by customer ID:
    // List<Order> findByCustomerId(Long customerId);

    // Additional methods can be added as per requirements
}
