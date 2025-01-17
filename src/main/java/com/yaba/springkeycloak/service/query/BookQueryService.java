package com.yaba.springkeycloak.service.query;

import com.yaba.springkeycloak.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookQueryService {

    Page<BookDto> getAll(Pageable pageable);

    BookDto getOne(UUID id);
}
