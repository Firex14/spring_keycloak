package com.yaba.springkeycloak.exchange.request.category;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryUpdateRequest {
    private UUID id;
    private String name;
    private String description;
}
