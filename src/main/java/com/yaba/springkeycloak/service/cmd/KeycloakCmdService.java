package com.yaba.springkeycloak.service.cmd;

import com.yaba.springkeycloak.exchange.request.user.UserCreateRequest;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakCmdService {

    UserRepresentation save(UserCreateRequest createRequest);
}
