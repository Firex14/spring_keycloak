package com.yaba.springkeycloak.exchange.response;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class BookResponse {
    private UUID id;
    private String title;
    private String author;
    private int stock;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private UUID categoryId;
}
