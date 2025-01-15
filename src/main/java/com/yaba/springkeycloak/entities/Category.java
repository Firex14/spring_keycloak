package com.yaba.springkeycloak.entities;

import jakarta.persistence.Entity;
import lombok.*;


@Getter
@Setter
@Entity
public class Category extends BaseModel{

    private String title;
    private String author;
    private int stock;

    public Category(){}

    public Category(String title,String author, int stock) {
        super();
        this.title = title;
        this.author = author;
       this.stock = stock;
    }
}
