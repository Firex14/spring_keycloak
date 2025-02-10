package com.yaba.springkeycloak.service.cmd.impl;

import com.yaba.springkeycloak.entities.Reader;
import com.yaba.springkeycloak.enums.ReaderStatus;
import com.yaba.springkeycloak.exceptions.ApiRequestException;
import com.yaba.springkeycloak.exceptions.ExceptionCode;
import com.yaba.springkeycloak.exceptions.ExceptionLevel;
import com.yaba.springkeycloak.exchange.request.reader.ReaderCreationRequest;
import com.yaba.springkeycloak.exchange.request.reader.ReaderUpdateRequest;
import com.yaba.springkeycloak.exchange.response.ReaderResponse;
import com.yaba.springkeycloak.mapper.reader.ReaderCreationMapper;
import com.yaba.springkeycloak.mapper.reader.ReaderMapper;
import com.yaba.springkeycloak.repository.ReaderRepository;
import com.yaba.springkeycloak.service.cmd.ReaderCmdService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReaderCmdServiceImpl implements ReaderCmdService {
    private final ReaderRepository repository;
    private final ReaderMapper mapper;
    private final ReaderCreationMapper creationMapper;

    public ReaderCmdServiceImpl(ReaderRepository repository, ReaderMapper mapper, ReaderCreationMapper creationMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.creationMapper = creationMapper;
    }

    @Override
    public ReaderResponse save(ReaderCreationRequest creationRequest) {
        if (existByEmail(creationRequest.getEmail())){
            throw new ApiRequestException(
                    ExceptionCode.READER_ALREADY_EXISTS.getMessage(),
                    ExceptionCode.READER_ALREADY_EXISTS.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.CONFLICT
                );
        }
        Reader reader = creationMapper.toEntity(creationRequest);
        reader.setStatus(ReaderStatus.ACTIVE);
        return mapper.toDto(repository.save(reader));
    }

    @Override
    public ReaderResponse update(ReaderUpdateRequest updateRequest) {
        return null;
    }

    @Override
    public ReaderResponse setStatus(ReaderStatus status) {
        return null;
    }
    private boolean existByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
