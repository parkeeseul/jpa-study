package com.seul.jpa.study.service;

import com.seul.jpa.study.domain.Address;
import com.seul.jpa.study.domain.Member;
import com.seul.jpa.study.domain.Order;
import com.seul.jpa.study.domain.OrderStatus;
import com.seul.jpa.study.domain.item.Book;
import com.seul.jpa.study.domain.item.Item;
import com.seul.jpa.study.exception.NotEnoughException;
import com.seul.jpa.study.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired private EntityManager em;
    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepository;

    private final int price = 1000;
    private final int stockQuantity = 10;
    private Member member;
    private Item item;

    @BeforeEach
    void setUp() {
        Address address = Address.of("서울", "거리", "123-123");

        member = Member.of("seul", address);
        em.persist(member);

        item = Book.createBook("JPA", price, stockQuantity, "김영한", "1234-5678");
        em.persist(item);
    }

    @Test
    void 상품주문() {

        int orderCount = 2;
        long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        Order findOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, findOrder.getStatus());
        assertEquals(1, findOrder.getOrderItems().size());
        assertEquals(orderCount * price, findOrder.getTotalPrice());
        assertEquals(stockQuantity - orderCount, item.getStockQuantity());
    }

    @Test
    void 상품주문_재고수량초과() {
        int orderCount = 11;

        assertThrows(NotEnoughException.class, () -> orderService.order(member.getId(), item.getId(), orderCount));
    }

    @Test
    void 주문취소() {
        int orderCount = 2;

        long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order findOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, findOrder.getStatus());
        assertEquals(10, item.getStockQuantity());
    }
}