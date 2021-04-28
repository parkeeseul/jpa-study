package com.seul.jpa.study.domain;

import com.seul.jpa.study.domain.item.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item", // 실무에서는 사용 X
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    protected Category(long id, String name, List<Item> items, Category parent, List<Category> child) {
        this.id = id;
        this.name = name;
        this.items = items;
        this.parent = parent;
        this.child = child;
    }
}
