package com.yaba.springkeycloak.service.query.impl;

import com.yaba.springkeycloak.exchange.response.CategoryResponse;
import com.yaba.springkeycloak.exceptions.ApiRequestException;
import com.yaba.springkeycloak.exceptions.ExceptionCode;
import com.yaba.springkeycloak.exceptions.ExceptionLevel;
import com.yaba.springkeycloak.mapper.category.CategoryMapper;
import com.yaba.springkeycloak.repository.CategoryRepository;
import com.yaba.springkeycloak.service.query.CategoryQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryQueryServiceImpl implements CategoryQueryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryQueryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<CategoryResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public CategoryResponse getOne(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new ApiRequestException(
                ExceptionCode.CATEGORY_NOT_FOUND.getMessage(),
                ExceptionCode.CATEGORY_NOT_FOUND.getValue(),
                ExceptionLevel.ERROR,
                HttpStatus.NOT_FOUND

        ));
    }
}
