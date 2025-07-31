package com.example.training.handsontwo.service;

import com.example.training.handsontwo.dto.OrderRequest;
import com.example.training.handsontwo.dto.OrderResponse;
import com.example.training.handsontwo.entity.Customer;
import com.example.training.handsontwo.entity.CookieOrder;
import com.example.training.handsontwo.repository.CustomerRepository;
import com.example.training.handsontwo.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public OrderResponse addOrder(OrderRequest orderRequest) {
        System.out.println("Start add order");
        Customer customer = customerRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        System.out.println(customer);
        CookieOrder cookieOrder = CookieOrder.builder()
                .orderNumber(orderRequest.getOrderNumber())
                .totalAmount(orderRequest.getTotalAmount())
                .customer(customer)
                .build();

        CookieOrder savedCookieOrder = orderRepository.saveAndFlush(cookieOrder);

        System.out.println(savedCookieOrder);

        customer.setBalance(customer.getBalance() - savedCookieOrder.getTotalAmount());
        customerRepository.save(customer);

        if(savedCookieOrder.getTotalAmount() > customer.getBalance()) {
            throw new RuntimeException("Insufficient balance for the order");
        } else if(savedCookieOrder.getTotalAmount() < 0) {
            throw new RuntimeException("Order amount cannot be negative");
        }

        return OrderResponse.builder()
                .orderNumber(savedCookieOrder.getOrderNumber())
                .orderDate(savedCookieOrder.getOrderDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .totalAmount(savedCookieOrder.getTotalAmount())
                .customerName(savedCookieOrder.getCustomer().getName())
                .currentBalance(customer.getBalance())
                .build();
    }
}
