package com.seul.jpa.study.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id") // 연관 관계의 주인
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    protected Order(long id, Member member, List<OrderItem> orderItems, Delivery delivery, LocalDateTime orderDate, OrderStatus status) {
        this.id = id;
        this.member = member;
        this.orderItems = orderItems;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.status = status;
    }

    // 연관관계 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
