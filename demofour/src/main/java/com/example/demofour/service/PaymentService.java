package com.example.demofour.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Retry(name="myRetry", fallbackMethod = "fallbackPayment")
    public String callPaymentAPI(){
        //Simulasi error
        if(Math.random()<0.7){
            throw new RuntimeException("API Payment gagal!");
        }
        return "Pembayaran berhasil";
    }

    public String fallbackPayment(){
        return "Gagal memproses pembayaran. Silakan coba lagi nanti";
    }
}
