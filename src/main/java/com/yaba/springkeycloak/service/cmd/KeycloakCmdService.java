package com.yaba.springkeycloak.service.cmd;

import com.yaba.springkeycloak.exchange.request.user.UserCreationRequest;
import com.yaba.springkeycloak.exchange.request.user.UserUpdateRequest;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakCmdService {

    UserRepresentation save(UserCreationRequest createRequest);
    UserRepresentation updateUser(UserUpdateRequest updateRequest);
}
