package com.yaba.springkeycloak.mapper.book;

import com.yaba.springkeycloak.exchange.response.BookResponse;
import com.yaba.springkeycloak.entities.Book;
import com.yaba.springkeycloak.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper extends BaseMapper<BookResponse, Book> {

    @Mapping(source = "category.id", target = "categoryId")
    BookResponse toDto(Book book);
}
