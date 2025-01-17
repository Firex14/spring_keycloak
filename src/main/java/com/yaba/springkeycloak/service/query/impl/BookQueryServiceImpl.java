package com.yaba.springkeycloak.service.query.impl;

import com.yaba.springkeycloak.dto.BookDto;
import com.yaba.springkeycloak.exceptions.ApiRequestException;
import com.yaba.springkeycloak.exceptions.ExceptionCode;
import com.yaba.springkeycloak.exceptions.ExceptionLevel;
import com.yaba.springkeycloak.mapper.BookMapper;
import com.yaba.springkeycloak.repository.BookRepository;
import com.yaba.springkeycloak.service.query.BookQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookQueryServiceImpl implements BookQueryService {
    private final BookRepository repository;
    private final BookMapper mapper;

    public BookQueryServiceImpl(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<BookDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public BookDto getOne(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(
                ()-> new ApiRequestException(
                        ExceptionCode.BOOK_NOT_FOUND.getMessage(),
                        ExceptionCode.BOOK_NOT_FOUND.getValue(),
                        ExceptionLevel.ERROR,
                        HttpStatus.NOT_FOUND
                )
        );
    }
}
