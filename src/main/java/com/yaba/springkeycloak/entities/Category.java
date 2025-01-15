package com.yaba.springkeycloak.entities;

import com.yaba.springkeycloak.validation.groups.Create;
import com.yaba.springkeycloak.validation.groups.Update;
import com.yaba.springkeycloak.validation.messages.ExceptionMessages;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Category extends BaseModel{

    @Column(nullable = false)
    @NotNull(message = ExceptionMessages.REQUIRED_FIELD, groups = { Create.class, Update.class })
    private String name;

    @Size(max = 500, message = ExceptionMessages.MAX_DESCRIPTION_SIZE, groups = { Create.class, Update.class})
    private String description;

    public Category() {}

    public Category(String name, String description){
        super();
        this.name = name;
        this.description = description;
    }
}
