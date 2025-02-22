package com.yaba.springkeycloak.service.cmd.impl;

import com.yaba.springkeycloak.exceptions.ApiRequestException;
import com.yaba.springkeycloak.exceptions.ExceptionCode;
import com.yaba.springkeycloak.exceptions.ExceptionLevel;
import com.yaba.springkeycloak.exchange.request.user.UserUpdateRequest;
import com.yaba.springkeycloak.exchange.request.user.UserCreationRequest;
import com.yaba.springkeycloak.service.cmd.KeycloakCmdService;
import com.yaba.springkeycloak.utils.PasswordUtil;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import jakarta.ws.rs.NotFoundException;


import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class KeycloakCmdServiceImpl implements KeycloakCmdService {

   private final Logger log = LoggerFactory.getLogger(KeycloakCmdServiceImpl.class);

   private final Keycloak keycloak;
   @Value("${application.keycloak.realm}")
   private String realm;

    public KeycloakCmdServiceImpl(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public UserRepresentation save(UserCreationRequest createRequest) {
        log.info("Request to save a user");

        UserRepresentation user = new UserRepresentation();
        user.setUsername(createRequest.getUsername());
        user.setEmail(createRequest.getEmail());
        user.setFirstName(createRequest.getFirstName());
        user.setLastName(createRequest.getLastName());
        user.setEnabled(true);

        CredentialRepresentation credential = PasswordUtil.createCredential();
        user.setCredentials(Collections.singletonList(credential));
        log.info("Generated Password: {}", credential.getValue());


        if (isUserExistByEmail(createRequest.getEmail()) || isUserExistByUsername(createRequest.getUsername())) {
            throw new ApiRequestException(
                    ExceptionCode.USER_ALREADY_EXISTS.getMessage(),
                    ExceptionCode.USER_ALREADY_EXISTS.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.CONFLICT
            );
        }

        try (Response response = keycloak.realm(realm).users().create(user)) {
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

                UserResource userResource = keycloak.realm(realm).users().get(userId);

                RoleRepresentation bibliothecaireRole = keycloak.realm(realm).roles().get("bibliothécaire").toRepresentation();

                userResource.roles().realmLevel().add(Collections.singletonList(bibliothecaireRole));

                log.info("User created and 'bibliothécaire' role assigned successfully.");
                return userResource.toRepresentation();
            } else {
                log.error("Failed to create user: {}", response.getStatusInfo());
                return null;
            }
        } catch (Exception e) {
            log.error("Error occurred while creating user", e);
            return null;
        }

    }
    @Override
    public UserRepresentation updateUser(UserUpdateRequest updateRequest) {
        log.info("Updating user: {}", updateRequest.getId());

        UserResource userResource = getUserById(updateRequest.getId());
        UserRepresentation user = userResource.toRepresentation();

        user.setFirstName(updateRequest.getFirstName());
        user.setLastName(updateRequest.getLastName());

        userResource.update(user);

        log.info("User {} updated successfully", updateRequest.getId());
        return userResource.toRepresentation();
    }


    private UserResource getUserById(UUID userId) {
        try {
            return keycloak.realm(realm).users().get(String.valueOf(userId));
        } catch (NotFoundException e) {
            throw new ApiRequestException(
                    ExceptionCode.USER_NOT_FOUND.getMessage(),
                    ExceptionCode.USER_NOT_FOUND.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            log.error("Error fetching user with ID {}", userId, e);
            throw new ApiRequestException(
                    ExceptionCode.INTERNAL_SERVER_ERROR.getMessage(),
                    ExceptionCode.INTERNAL_SERVER_ERROR.getValue(),
                    ExceptionLevel.ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }



    private boolean isUserExistByUsername(String username) {
        List<UserRepresentation> users = keycloak.realm(realm).users().search(username, 0, 10);

        return users.stream().anyMatch(user ->
                username.equalsIgnoreCase(user.getUsername())
        );
    }

    private boolean isUserExistByEmail(String email) {
        // Rechercher un utilisateur par email
        List<UserRepresentation> users = keycloak.realm(realm).users().search(email, 0, 10);

        return users.stream().anyMatch(user ->
                email.equalsIgnoreCase(user.getEmail())
        );
    }




}
