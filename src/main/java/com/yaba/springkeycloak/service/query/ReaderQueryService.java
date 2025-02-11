package com.yaba.springkeycloak.service.query;

import com.yaba.springkeycloak.exchange.response.ReaderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReaderQueryService {

    Page<ReaderResponse> getAll(Pageable pageable);
    ReaderResponse getOne(UUID id);
}
