package com.yaba.springkeycloak.service.query;

import com.yaba.springkeycloak.exchange.response.ReaderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReaderQueryService {

    Page<ReaderResponse> getAll(Pageable pageable);
}
