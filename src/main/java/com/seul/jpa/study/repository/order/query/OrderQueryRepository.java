package com.seul.jpa.study.repository.order.query;

import com.seul.jpa.study.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders();

        result.forEach(orderQueryDto -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(orderQueryDto.getOrderId());
            orderQueryDto.setOrderItems(orderItems);
        });

        return result;
    }

    private List<OrderItemQueryDto> findOrderItems(long orderId) {
        return em.createQuery(
                "SELECT new com.seul.jpa.study.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                        " FROM OrderItem oi" +
                        " JOIN oi.item i" +
                        " WHERE oi.order.id = :ORDER_ID", OrderItemQueryDto.class)
                .setParameter("ORDER_ID", orderId)
                .getResultList();
    }

    private List<OrderQueryDto> findOrders() {
        return em.createQuery(
                "SELECT new com.seul.jpa.study.repository.order.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                        " FROM Order o" +
                        " JOIN o.member m" +
                        " JOIN o.delivery d", OrderQueryDto.class)
                .getResultList();
    }

}
