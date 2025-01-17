package com.yaba.springkeycloak.service.cmd;

import com.yaba.springkeycloak.dto.BookDto;

public interface BookCmdService {

    BookDto save(BookDto bookDto);
    BookDto update(BookDto bookDto);
}
