package com.example.training.handsontwo.controller;

import com.example.training.handsontwo.dto.OrderRequest;
import com.example.training.handsontwo.dto.OrderResponse;
import com.example.training.handsontwo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add")
    public OrderResponse addOrder(@RequestBody OrderRequest orderRequest){
        System.out.println("Received order request: " + orderRequest);
        return orderService.addOrder(orderRequest);
    }

}
