package com.yaba.springkeycloak.exchange.request.book;

import lombok.*;

import java.util.UUID;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class BookUpdateRequest {
    private UUID id;
    private String title;
    private String author;
    private int stock;
    private UUID categoryId;
}
