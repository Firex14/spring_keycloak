package com.yaba.springkeycloak.mapper.reader;

import com.yaba.springkeycloak.entities.Reader;
import com.yaba.springkeycloak.exchange.request.reader.ReaderCreationRequest;
import com.yaba.springkeycloak.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReaderCreationMapper extends BaseMapper<ReaderCreationRequest, Reader> {
}
