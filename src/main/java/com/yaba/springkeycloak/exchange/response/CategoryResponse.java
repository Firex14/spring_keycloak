package com.yaba.springkeycloak.exchange.response;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private UUID id;
    private String name;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private String description;
}
