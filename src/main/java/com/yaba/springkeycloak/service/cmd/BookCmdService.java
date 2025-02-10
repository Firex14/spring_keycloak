package com.yaba.springkeycloak.service.cmd;

import com.yaba.springkeycloak.exchange.request.book.BookCreationRequest;
import com.yaba.springkeycloak.exchange.request.book.BookUpdateRequest;
import com.yaba.springkeycloak.exchange.response.BookResponse;

public interface BookCmdService {

    BookResponse save(BookCreationRequest createRequest);
    BookResponse update(BookUpdateRequest updateRequest);
}
