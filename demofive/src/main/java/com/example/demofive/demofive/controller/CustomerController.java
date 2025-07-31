package com.example.demofive.demofive.controller;

import com.example.demofive.demofive.dto.CustomerRequest;
import com.example.demofive.demofive.dto.CustomerResponse;
import com.example.demofive.demofive.entity.Customer;
import com.example.demofive.demofive.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.saveCustomer(customerRequest));
    }

    @GetMapping("/all")
    public Page<Customer> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy,
                                          @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return customerService.findAll(pageable);
    }

    @GetMapping("/phone-number")
    public Page<Customer> getCustomersByPhoneNumber(
            @RequestParam(name = "phoneNumber") String phoneNumber,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return customerService.getByPhoneNumber(pageable, phoneNumber);
    }

    @GetMapping("/name")
    public Page<Customer> getCustomersByName(
            @RequestParam(name = "name") String phoneNumber,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return customerService.getByPhoneNumber(pageable, phoneNumber);
    }

    @GetMapping("/check-lazy")
    public String testLazy() {
        customerService.testLazy();
        return "Lazy loading test completed. Check console for output.";
    }

    @GetMapping("/check-eager")
    public String testEager() {
        customerService.testEager();
        return "Eager loading test completed. Check console for output.";
    }

    @GetMapping("/kasus-n-plus-one")
    public List<Customer> getCustomerNPlusOne() {
        return customerService.getCustomersKasusNPlus1();
    }

    @GetMapping("/penanganan-n-plus-one")
    public List<Customer> getCustomersPenangananNPlusOne() {
        return customerService.getCustomersPenangananNPlus1();
    }
}
