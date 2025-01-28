package com.yaba.springkeycloak.exchange.request.category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCreateRequest {
    private String name;
    private String description;
}
