package com.yaba.springkeycloak.service.cmd;

import com.yaba.springkeycloak.dto.CategoryDto;

public interface CategoryCmdService {

    CategoryDto save(CategoryDto dto);

    CategoryDto update(CategoryDto dto);

}
