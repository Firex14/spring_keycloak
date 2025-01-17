package com.yaba.springkeycloak.mapper;

import com.yaba.springkeycloak.dto.BookDto;
import com.yaba.springkeycloak.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper extends BaseMapper<BookDto, Book> {

    @Mapping(source = "category.id", target = "categoryId")
    BookDto toDto(Book book);
}
