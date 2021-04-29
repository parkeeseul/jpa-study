package com.seul.jpa.study.domain.item;

import com.seul.jpa.study.domain.Category;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;

    protected Book(String name, int price, int stockQuantity, List<Category> categories, String author, String isbn) {
        super(name, price, stockQuantity, categories);
        this.author = author;
        this.isbn = isbn;
    }
}
