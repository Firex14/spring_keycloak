package com.yaba.springkeycloak.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String title;
    private String author;
    private int stock;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
