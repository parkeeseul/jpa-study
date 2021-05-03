package com.seul.jpa.study.domain.item;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@NoArgsConstructor
public class Book extends Item {

    private String author;
    private String isbn;

    private Book(String name, int price, int stockQuantity, String author, String isbn) {
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    public static Book createBook(String name, int price, int stockQuantity, String author, String isbn) {
        return new Book(name, price, stockQuantity, author, isbn);
    }
}
