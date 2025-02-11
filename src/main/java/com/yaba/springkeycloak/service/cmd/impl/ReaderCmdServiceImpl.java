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

import java.util.UUID;

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
        if (updateRequest.getId() == null) {
            throw new ApiRequestException(
                    ExceptionCode.NULL_VALUE_OF_ID.getMessage(),
                    ExceptionCode.NULL_VALUE_OF_ID.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.BAD_REQUEST);
        }
        return repository.findById(updateRequest.getId()).map(
                reader -> {
                    if (existExceptCurrent(updateRequest.getEmail(), reader.getId())) {
                        throw new ApiRequestException(
                                ExceptionCode.READER_ALREADY_EXISTS.getMessage(),
                                ExceptionCode.READER_ALREADY_EXISTS.getValue(),
                                ExceptionLevel.ERROR,
                                HttpStatus.CONFLICT
                        );
                    }
                    reader.setEmail(updateRequest.getEmail());
                    reader.setFirstName(updateRequest.getFirstName());
                    reader.setLastName(updateRequest.getLastName());
                    return mapper.toDto(repository.save(reader));
                }
                )
                .orElseThrow(
                () -> new ApiRequestException(
                        ExceptionCode.READER_NOT_FOUND.getMessage(),
                        ExceptionCode.READER_NOT_FOUND.getValue(),
                        ExceptionLevel.ERROR,
                        HttpStatus.NOT_FOUND
                    )
            );
    }

    @Override
    public ReaderResponse setStatus(UUID id, ReaderStatus status) {
        return repository.findById(id).map(
                reader -> {
                    reader.setStatus(status);
                    return mapper.toDto(repository.save(reader));
                }
        ).orElseThrow(
                () -> new ApiRequestException(
                        ExceptionCode.READER_NOT_FOUND.getMessage(),
                        ExceptionCode.READER_NOT_FOUND.getValue(),
                        ExceptionLevel.ERROR,
                        HttpStatus.NOT_FOUND
                    )

        );
    }
    private boolean existByEmail(String email) {
        return repository.existsByEmail(email);
    }

    private boolean existExceptCurrent(String email, UUID readerId) {
        return repository.existsByEmailAndIdNot(email, readerId);
    }
}
