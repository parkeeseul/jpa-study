package com.seul.jpa.study.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {

    private String author;
    private String isbn;

    protected Book(String author, String isbn) {
        this.author = author;
        this.isbn = isbn;
    }
}
