package com.yaba.springkeycloak.service.query.impl;

import com.yaba.springkeycloak.dto.CategoryDto;
import com.yaba.springkeycloak.mapper.CategoryMapper;
import com.yaba.springkeycloak.repository.CategoryRepository;
import com.yaba.springkeycloak.service.query.CategoryQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryQueryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<CategoryDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }
}
