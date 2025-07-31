package com.example.training.handsontwo.service;

import com.example.training.handsontwo.dto.CustomerRequest;
import com.example.training.handsontwo.dto.CustomerResponse;
import com.example.training.handsontwo.entity.Customer;
import com.example.training.handsontwo.entity.CookieOrder;
import com.example.training.handsontwo.repository.CustomerRepository;
import com.example.training.handsontwo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .balance(customerRequest.getBalance())
                .build();

        Customer savedCustomer = customerRepository.save(customer);
        return CustomerResponse.builder()
                .name(savedCustomer.getName())
                .email(savedCustomer.getEmail())
                .balance(savedCustomer.getBalance())
                .build();
    }

    public void testLazy() {
        System.out.println("=== LAZY TEST START ===");
        Customer customer = customerRepository.findById(1).orElseThrow();

        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Jumlah Order: " + customer.getCookieOrderList().size());
    }

    public void testEager() {
        System.out.println("=== EAGER TEST START ===");
        CookieOrder cookieOrder = orderRepository.findById(1).orElseThrow();

        System.out.println("Customer Name: " + cookieOrder.getCustomer().getName());
        System.out.println("OrderNumber: " + cookieOrder.getOrderNumber());
        System.out.println("OrderAmount: " + cookieOrder.getTotalAmount());
    }
}
