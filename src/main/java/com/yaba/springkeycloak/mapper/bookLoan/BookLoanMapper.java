package com.yaba.springkeycloak.mapper.bookLoan;

import com.yaba.springkeycloak.entities.BookLoan;
import com.yaba.springkeycloak.exchange.response.BookLoanResponse;
import com.yaba.springkeycloak.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookLoanMapper extends BaseMapper<BookLoanResponse, BookLoan> {
    @Mapping(source = "reader.id", target = "readerId")
    @Mapping(source = "book.id", target = "bookId")
    BookLoanResponse toDto(BookLoan bookLoan);
}
