package com.training.demosix.controller;

import com.training.demosix.dto.CustomerRequest;
import com.training.demosix.dto.CustomerResponse;
import com.training.demosix.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/")
    public String createCustomer(@RequestBody CustomerRequest customerRequest){
        return customerService.createCustomer(customerRequest);
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }
}
