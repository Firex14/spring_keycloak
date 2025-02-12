package com.yaba.springkeycloak.mapper.reader;

import com.yaba.springkeycloak.entities.Reader;
import com.yaba.springkeycloak.exchange.response.ReaderResponse;
import com.yaba.springkeycloak.mapper.BaseMapper;
import com.yaba.springkeycloak.mapper.bookLoan.BookLoanMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BookLoanMapper.class)
public interface ReaderMapper extends BaseMapper<ReaderResponse, Reader> {


    @Mapping(source = "borrowedBooks", target = "borrowedBooks") 
    ReaderResponse toDto(Reader reader);
}
