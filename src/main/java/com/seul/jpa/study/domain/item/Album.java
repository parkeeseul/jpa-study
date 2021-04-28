package com.seul.jpa.study.domain.item;

import com.seul.jpa.study.domain.Category;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
    private String etc;

    protected Album(String name, int price, int stockQuantity, List<Category> categories, String artist, String etc) {
        super(name, price, stockQuantity, categories);
        this.artist = artist;
        this.etc = etc;
    }
}
