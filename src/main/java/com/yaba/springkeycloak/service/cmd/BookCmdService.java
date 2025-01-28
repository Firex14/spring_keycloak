package com.yaba.springkeycloak.service.cmd;

import com.yaba.springkeycloak.exchange.request.book.BookCreateRequest;
import com.yaba.springkeycloak.exchange.request.book.BookUpdateRequest;
import com.yaba.springkeycloak.exchange.response.BookResponse;

public interface BookCmdService {

    BookResponse save(BookCreateRequest createRequest);
    BookResponse update(BookUpdateRequest updateRequest);
}
