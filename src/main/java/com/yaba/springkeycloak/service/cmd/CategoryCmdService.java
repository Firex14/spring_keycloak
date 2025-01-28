package com.yaba.springkeycloak.service.cmd;

import com.yaba.springkeycloak.exchange.request.category.CategoryCreateRequest;
import com.yaba.springkeycloak.exchange.request.category.CategoryUpdateRequest;
import com.yaba.springkeycloak.exchange.response.CategoryResponse;

public interface CategoryCmdService {

    CategoryResponse save(CategoryCreateRequest dto);

    CategoryResponse update(CategoryUpdateRequest dto);

}
