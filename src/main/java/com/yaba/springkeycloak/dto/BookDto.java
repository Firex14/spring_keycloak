package com.yaba.springkeycloak.dto;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class BookDto {
    private UUID id;
    private String title;
    private String author;
    private int stock;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private UUID categoryId;
}
