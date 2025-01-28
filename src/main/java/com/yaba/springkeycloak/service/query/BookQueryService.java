package com.yaba.springkeycloak.service.query;

import com.yaba.springkeycloak.exchange.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookQueryService {

    Page<BookResponse> getAll(Pageable pageable);

    BookResponse getOne(UUID id);
}
