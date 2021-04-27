package com.seul.jpa.study.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    protected Delivery(long id, Order order, Address address, DeliveryStatus status) {
        this.id = id;
        this.order = order;
        this.address = address;
        this.status = status;
    }
}
