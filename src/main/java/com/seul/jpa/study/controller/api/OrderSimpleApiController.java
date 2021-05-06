package com.seul.jpa.study.controller.api;

import com.seul.jpa.study.domain.Order;
import com.seul.jpa.study.repository.OrderRepository;
import com.seul.jpa.study.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());

        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getId();
            
        }
        return all;
    }

}
