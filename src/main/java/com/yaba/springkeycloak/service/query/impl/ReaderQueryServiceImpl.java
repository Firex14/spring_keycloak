package com.yaba.springkeycloak.service.query.impl;

import com.yaba.springkeycloak.exceptions.ApiRequestException;
import com.yaba.springkeycloak.exceptions.ExceptionCode;
import com.yaba.springkeycloak.exceptions.ExceptionLevel;
import com.yaba.springkeycloak.exchange.response.ReaderResponse;
import com.yaba.springkeycloak.mapper.reader.ReaderMapper;
import com.yaba.springkeycloak.repository.ReaderRepository;
import com.yaba.springkeycloak.service.query.ReaderQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReaderQueryServiceImpl implements ReaderQueryService {
    private final ReaderRepository repository;
    private final ReaderMapper mapper;

    public ReaderQueryServiceImpl(ReaderRepository repository, ReaderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<ReaderResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public ReaderResponse getOne(UUID id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(() -> new ApiRequestException(
                ExceptionCode.READER_NOT_FOUND.getMessage(),
                ExceptionCode.READER_NOT_FOUND.getValue(),
                ExceptionLevel.ERROR,
                HttpStatus.NOT_FOUND

        ));
    }
}
