package com.seul.jpa.study.domain;

import com.seul.jpa.study.domain.item.Item;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order oder;

    private int orderPrice;
    private int count;
}
