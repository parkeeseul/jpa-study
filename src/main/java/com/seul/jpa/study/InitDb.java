package com.seul.jpa.study;

import com.seul.jpa.study.domain.*;
import com.seul.jpa.study.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Address address = Address.of("서울","거리","111-111");
            Member member = Member.of("userA", address);
            em.persist(member);

            Book book1 = Book.createBook("JPA1 BOOK", 10000, 100, "JPA 작가1", "111-222");
            em.persist(book1);

            Book book2 = Book.createBook("JPA2 BOOK", 20000, 100, "JPA 작가2", "222-333");
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = Delivery.createDelivery(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2() {
            Address address = Address.of("청주","거리2","111-111");
            Member member = Member.of("userB", address);
            em.persist(member);

            Book book1 = Book.createBook("SPRING1 BOOK", 20000, 200, "SPRING 작가1", "111-222");
            em.persist(book1);

            Book book2 = Book.createBook("SPRING2 BOOK", 40000, 300, "SPRING 작가2", "222-333");
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            Delivery delivery = Delivery.createDelivery(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
    }
}
