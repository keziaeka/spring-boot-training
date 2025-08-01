package com.training.demosix.repository;

import com.training.demosix.entity.CookieOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<CookieOrder, Long> {

    // Additional query methods can be defined here if needed
    // For example, to find orders by customer ID:
     List<CookieOrder> findByCustomerId(Long customerId);
}
