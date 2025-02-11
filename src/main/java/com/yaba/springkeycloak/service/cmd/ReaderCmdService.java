package com.yaba.springkeycloak.service.cmd;

import com.yaba.springkeycloak.enums.ReaderStatus;
import com.yaba.springkeycloak.exchange.request.reader.ReaderCreationRequest;
import com.yaba.springkeycloak.exchange.request.reader.ReaderUpdateRequest;
import com.yaba.springkeycloak.exchange.response.ReaderResponse;

import java.util.UUID;

public interface ReaderCmdService {
    ReaderResponse save(ReaderCreationRequest creationRequest);
    ReaderResponse update(ReaderUpdateRequest updateRequest);
    ReaderResponse setStatus(UUID id,ReaderStatus status);
}
