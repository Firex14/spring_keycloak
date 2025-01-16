package com.yaba.springkeycloak.service.query;

import com.yaba.springkeycloak.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryQueryService {

    Page<CategoryDto> getAll(Pageable pageable);
}
