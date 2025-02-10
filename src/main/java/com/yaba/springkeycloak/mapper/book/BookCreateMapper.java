package com.yaba.springkeycloak.mapper.book;

import com.yaba.springkeycloak.entities.Book;
import com.yaba.springkeycloak.exchange.request.book.BookCreationRequest;
import com.yaba.springkeycloak.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookCreateMapper extends BaseMapper<BookCreationRequest, Book> {
}
