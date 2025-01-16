package com.yaba.springkeycloak.mapper;

import com.yaba.springkeycloak.dto.CategoryDto;
import com.yaba.springkeycloak.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryDto, Category>{
}
