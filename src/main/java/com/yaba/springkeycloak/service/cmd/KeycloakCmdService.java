package com.yaba.springkeycloak.service.cmd;

import com.yaba.springkeycloak.exchange.request.user.UserCreateRequest;
import com.yaba.springkeycloak.exchange.request.user.UserUpdateRequest;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakCmdService {

    UserRepresentation save(UserCreateRequest createRequest);
    UserRepresentation updateUser(UserUpdateRequest updateRequest);
}
