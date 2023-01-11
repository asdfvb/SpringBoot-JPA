package jpaBook.jspShop.domain;

import javax.persistence.Entity;

@Entity
public class Albums extends Items {
    private String artist;
    private String etc;
}
