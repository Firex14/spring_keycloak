package com.yaba.springkeycloak.service.query.impl;

import com.yaba.springkeycloak.exchange.response.ReaderResponse;
import com.yaba.springkeycloak.mapper.reader.ReaderMapper;
import com.yaba.springkeycloak.repository.ReaderRepository;
import com.yaba.springkeycloak.service.query.ReaderQueryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
