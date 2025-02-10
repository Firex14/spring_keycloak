package com.yaba.springkeycloak.service.cmd;

import com.yaba.springkeycloak.enums.ReaderStatus;
import com.yaba.springkeycloak.exchange.request.reader.ReaderCreationRequest;
import com.yaba.springkeycloak.exchange.request.reader.ReaderUpdateRequest;
import com.yaba.springkeycloak.exchange.response.ReaderResponse;

public interface ReaderCmdService {
    ReaderResponse save(ReaderCreationRequest creationRequest);
    ReaderResponse update(ReaderUpdateRequest updateRequest);
    ReaderResponse setStatus(ReaderStatus status);
}
