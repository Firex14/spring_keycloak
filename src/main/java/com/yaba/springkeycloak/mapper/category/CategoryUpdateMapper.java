package com.yaba.springkeycloak.mapper.category;

import com.yaba.springkeycloak.entities.Category;
import com.yaba.springkeycloak.exchange.request.category.CategoryUpdateRequest;
import com.yaba.springkeycloak.exchange.response.CategoryResponse;
import com.yaba.springkeycloak.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryUpdateMapper extends BaseMapper<CategoryUpdateRequest, Category> {
}
