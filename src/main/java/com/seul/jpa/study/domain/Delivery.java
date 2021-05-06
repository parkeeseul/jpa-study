package com.seul.jpa.study.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private Delivery(Address address) {
        this.address = address;
    }

    public static Delivery createDelivery(Address address) {
        return new Delivery(address);
    }
}
