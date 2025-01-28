package com.yaba.springkeycloak.service.cmd.impl;

import com.yaba.springkeycloak.exchange.request.category.CategoryCreateRequest;
import com.yaba.springkeycloak.exchange.request.category.CategoryUpdateRequest;
import com.yaba.springkeycloak.exchange.response.CategoryResponse;
import com.yaba.springkeycloak.entities.Category;
import com.yaba.springkeycloak.exceptions.ApiRequestException;
import com.yaba.springkeycloak.exceptions.ExceptionCode;
import com.yaba.springkeycloak.exceptions.ExceptionLevel;
import com.yaba.springkeycloak.mapper.category.CategoryMapper;
import com.yaba.springkeycloak.mapper.category.CategoryUpdateMapper;
import com.yaba.springkeycloak.repository.CategoryRepository;
import com.yaba.springkeycloak.service.cmd.CategoryCmdService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryCmdServiceImpl implements CategoryCmdService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;
    private final CategoryUpdateMapper categoryUpdateMapper;

    public CategoryCmdServiceImpl(CategoryRepository repository, CategoryMapper mapper, CategoryUpdateMapper categoryUpdateMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.categoryUpdateMapper = categoryUpdateMapper;
    }

    @Override
    public CategoryResponse save(CategoryCreateRequest createRequest) {
        if (isExist(createRequest.getName())){
            throw new ApiRequestException(
                    ExceptionCode.CATEGORY_ALREADY_EXISTS.getMessage(),
                    ExceptionCode.CATEGORY_ALREADY_EXISTS.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.CONFLICT
                );
            }
        Category newCategory = new Category();
        newCategory.setName(createRequest.getName());
        newCategory.setDescription(createRequest.getDescription());

        return mapper.toDto(repository.save(newCategory));
    }

    @Override
    public CategoryResponse update(CategoryUpdateRequest updateRequest) {
        if (updateRequest.getId() == null) {
            throw new ApiRequestException(
                    ExceptionCode.NULL_VALUE_OF_ID.getMessage(),
                    ExceptionCode.NULL_VALUE_OF_ID.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.BAD_REQUEST

            );
        }
        return repository.findById(updateRequest.getId()).map(
                category -> {
                    if (isExistExceptCurrent(updateRequest.getName(), category.getId())) {
                        throw new ApiRequestException(
                                ExceptionCode.CATEGORY_ALREADY_EXISTS.getMessage(),
                                ExceptionCode.CATEGORY_ALREADY_EXISTS.getValue(),
                                ExceptionLevel.ERROR,
                                HttpStatus.CONFLICT
                        );
                    }
                    categoryUpdateMapper.partialUpdate(category, updateRequest);
                    return mapper.toDto(repository.save(category));
                }
        ).orElseThrow(()-> new ApiRequestException(
                ExceptionCode.CATEGORY_NOT_FOUND.getMessage(),
                ExceptionCode.CATEGORY_NOT_FOUND.getValue(),
                ExceptionLevel.ERROR,
                HttpStatus.NOT_FOUND
        ));
    }

    private boolean isExist(String name) {
        return repository.existsByNameIgnoreCase(name);
    }

    private boolean isExistExceptCurrent(String name, UUID categoryId) {
        return repository.existsByNameIgnoreCaseAndIdNot(name, categoryId);
    }

}
