package com.seul.jpa.study.domain;

import com.seul.jpa.study.domain.item.Item;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order oder;

    private int orderPrice;
    private int count;

    protected OrderItem(long id, Item item, Order oder, int orderPrice, int count) {
        this.id = id;
        this.item = item;
        this.oder = oder;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
