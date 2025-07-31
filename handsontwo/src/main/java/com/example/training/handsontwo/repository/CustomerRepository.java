package com.example.training.handsontwo.repository;

import com.example.training.handsontwo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Custom query methods can be defined here if needed
    // For example:
    // List<Customer> findByLastName(String lastName);

    // The JpaRepository interface provides basic CRUD operations out of the box
}
