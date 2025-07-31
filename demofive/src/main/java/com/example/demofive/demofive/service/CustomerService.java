package com.example.demofive.demofive.service;

import com.example.demofive.demofive.dto.CustomerRequest;
import com.example.demofive.demofive.dto.CustomerResponse;
import com.example.demofive.demofive.entity.BankAccount;
import com.example.demofive.demofive.entity.Customer;
import com.example.demofive.demofive.repository.BankAccountRepository;
import com.example.demofive.demofive.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ModelMapper modelMapper;

    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {

        Customer customer = modelMapper.map(customerRequest, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerResponse.class);

//        Customer customer = new Customer();
//        customer.setName(customerRequest.getCustomerName());
//        customer.setAddress(customerRequest.getCustomerAddress());
//
//        customerRepository.save(customer);
//        return CustomerResponse.builder()
//                .customerName(customer.getName())
//                .customerAddress(customer.getAddress())
//                .build();
    }

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Page<Customer> getByPhoneNumber(Pageable pageable, String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber);
        if (customer != null) {
            return customerRepository.findAll(pageable); // Return all customers if found
        } else {
            return Page.empty(pageable); // Return empty page if not found
        }
    }

    public Page<Customer> getName(Pageable pageable, String name) {
        return customerRepository.findByCustomerName(name, pageable);
    }

    public void testLazy() {
        System.out.println("=== LAZY TEST START ===");
        Customer customer = customerRepository.findById(1).orElseThrow();

        System.out.println("Customer Name: " + customer.getCustomerName());
        System.out.println("Jumlah Akun: " + customer.getBankAccountList().size());
    }

    public void testEager() {
        System.out.println("=== EAGER TEST START ===");
        BankAccount account = bankAccountRepository.findById(1).orElseThrow();

        System.out.println("Card Number: " + account.getCardNumber());
        System.out.println("Customer Name: " + account.getCustomer().getCustomerName());
    }

    public List<Customer> getCustomersKasusNPlus1() {
        List<Customer> customers = customerRepository.findAll();
        for (Customer c : customers) {
            c.getBankAccountList().size();
        }
        return customers;
    }

    public List<Customer> getCustomersPenangananNPlus1() {
        List<Customer> customers = customerRepository.findAllWithAccounts();
        return customers;
    }

}
