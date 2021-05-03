package com.seul.jpa.study.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // Order.member 와 맵핑 되는 정보 (연관 관계의 주인 X)
    private List<Order> orders = new ArrayList<>();

    protected Member(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public static Member of(String name, Address address) {
        return new Member(name, address);
    }
}
