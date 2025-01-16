package com.yaba.springkeycloak.service.cmd.impl;

import com.yaba.springkeycloak.dto.CategoryDto;
import com.yaba.springkeycloak.entities.Category;
import com.yaba.springkeycloak.exceptions.ApiRequestException;
import com.yaba.springkeycloak.exceptions.ExceptionCode;
import com.yaba.springkeycloak.exceptions.ExceptionLevel;
import com.yaba.springkeycloak.mapper.CategoryMapper;
import com.yaba.springkeycloak.repository.CategoryRepository;
import com.yaba.springkeycloak.service.cmd.CategoryCmdService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryCmdServiceImpl implements CategoryCmdService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryCmdServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        if (isExist(dto.getName())){
            throw new ApiRequestException(
                    ExceptionCode.CATEGORY_ALREADY_EXISTS.getMessage(),
                    ExceptionCode.CATEGORY_ALREADY_EXISTS.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.CONFLICT
                );
            }
        Category newCategory = new Category();
        newCategory.setName(dto.getName());
        newCategory.setDescription(dto.getDescription());

        return mapper.toDto(repository.save(newCategory));
    }

    @Override
    public CategoryDto update(CategoryDto dto) {
        if (dto.getId() == null) {
            throw new ApiRequestException(
                    ExceptionCode.CATEGORY_NOT_FOUND.getMessage(),
                    ExceptionCode.CATEGORY_NOT_FOUND.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.NOT_FOUND

            );
        }
        return repository.findById(dto.getId()).map(
                category -> {
                    mapper.partialUpdate(category, dto);
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

}
