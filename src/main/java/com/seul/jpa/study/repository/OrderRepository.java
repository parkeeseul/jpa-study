package com.seul.jpa.study.repository;

import com.seul.jpa.study.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        String jpql = "SELECT o FROM Order o JOIN o.member m ";

        return em.createQuery(jpql, Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList();
    }
}
