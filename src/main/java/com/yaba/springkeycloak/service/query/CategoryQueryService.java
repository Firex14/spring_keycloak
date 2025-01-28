package com.yaba.springkeycloak.service.query;

import com.yaba.springkeycloak.exchange.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CategoryQueryService {

    Page<CategoryResponse> getAll(Pageable pageable);
    CategoryResponse getOne(UUID id);
}
