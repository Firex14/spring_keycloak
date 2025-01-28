package com.yaba.springkeycloak.service.query.impl;

import com.yaba.springkeycloak.service.query.KeycloakQueryService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakQueryServiceImpl implements KeycloakQueryService {
    private final Logger log = LoggerFactory.getLogger(KeycloakQueryServiceImpl.class);


    private final Keycloak keycloak;

    @Value("${application.keycloak.realm}")
    private String realm;

    public KeycloakQueryServiceImpl(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public UserRepresentation getUser(String username) {
        return null;
    }

    @Override
    public List<UserRepresentation> getUsers() {
        log.info("Getting users from Keycloak...");
        return keycloak.realm(realm).users().list();
    }
}
