package com.example.training.handsontwo.controller;

import com.example.training.handsontwo.dto.CustomerRequest;
import com.example.training.handsontwo.dto.CustomerResponse;
import com.example.training.handsontwo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add")
    public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest){
        return customerService.createCustomer(customerRequest);
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
}
