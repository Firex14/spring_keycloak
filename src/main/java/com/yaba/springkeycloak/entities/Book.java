package com.yaba.springkeycloak.entities;

import com.yaba.springkeycloak.validation.groups.Create;
import com.yaba.springkeycloak.validation.groups.Update;
import com.yaba.springkeycloak.validation.messages.ExceptionMessages;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@Entity
public class Book extends BaseModel{

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.REQUIRED_FIELD, groups = { Create.class, Update.class })
    private String title;

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.REQUIRED_FIELD, groups = { Create.class, Update.class })
    private String author;

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.REQUIRED_FIELD, groups = { Create.class, Update.class })
    @Min(value = 0, message = ExceptionMessages.MIN_STOCK_VALUE, groups = { Create.class, Update.class })
    private int stock;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Book(){}

    public Book(String title, String author, int stock) {
        super();
        this.title = title;
        this.author = author;
       this.stock = stock;
    }
}
