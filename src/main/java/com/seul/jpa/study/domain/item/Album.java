package com.seul.jpa.study.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
    private String etc;

    protected Album(String artist, String etc) {
        this.artist = artist;
        this.etc = etc;
    }
}
