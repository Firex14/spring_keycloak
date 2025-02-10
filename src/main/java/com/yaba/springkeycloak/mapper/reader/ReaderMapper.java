package com.yaba.springkeycloak.mapper.reader;

import com.yaba.springkeycloak.entities.Reader;
import com.yaba.springkeycloak.exchange.response.ReaderResponse;
import com.yaba.springkeycloak.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReaderMapper extends BaseMapper<ReaderResponse, Reader> {
}
