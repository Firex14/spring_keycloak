package com.yaba.springkeycloak.service.query;

import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface KeycloakQueryService {

    UserRepresentation getUser(String username);
    List<UserRepresentation> getUsers();
}
