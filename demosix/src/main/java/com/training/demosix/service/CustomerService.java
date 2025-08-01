package com.training.demosix.service;

import com.training.demosix.dto.CustomerRequest;
import com.training.demosix.dto.CustomerResponse;
import com.training.demosix.entity.Customer;
import com.training.demosix.repository.CustomerRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

//    private int attempt = 0; // counter untuk simulasi retry

    public String createCustomer(CustomerRequest customerRequest){
        Customer customer = new Customer();
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setBalance(customerRequest.getBalance());

        customerRepository.save(customer);
        return "Success";
    }

    @CircuitBreaker(name = "customerService", fallbackMethod = "fallbackCustomer")
    @Retry(name = "customerService", fallbackMethod = "fallbackCustomer")
    public CustomerResponse getCustomerById(Long id){
        simulateExternalCall();
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer tidak ditemukan"));
        return new CustomerResponse(customer.getId(), customer.getBalance(), "Data berhasil diambil");
    }

    // Fallback method dipanggil tiap kali retry gagal
    public CustomerResponse fallbackCustomer(Long id, Throwable t) {
        String message = t.getMessage();
        if (t instanceof RetryException) {
            return new CustomerResponse(id, 0.0, message);
        }
        return new CustomerResponse(id, 0.0, message);
    }

//    private void simulateExternalCall() {
//        attempt++;
//        if (attempt <= 2) {
//            System.out.println("Simulasi gagal pada percobaan ke-" + attempt);
//            throw new RuntimeException("Simulasi error ke-" + attempt);
//        } else {
//            System.out.println("Simulasi sukses pada percobaan ke-" + attempt);
//        }
//    }

    private int attempt = 1;
    private void simulateExternalCall() {
        if (attempt <= 3) {
            throw new RetryException("Retry " + attempt++);
        }
    }

    public static class RetryException extends RuntimeException {
        public RetryException(String message) {
            super(message);
        }
    }

}
