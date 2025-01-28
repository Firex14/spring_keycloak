package com.yaba.springkeycloak.config;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import jakarta.ws.rs.client.Client;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Bean
    public Keycloak keycloak(@Value("${application.keycloak.baseurl}") String baseUrl,
                             @Value("${application.keycloak.client-secret}") String clientSecret) {

        Client client = new ResteasyClientBuilderImpl()
                .connectionPoolSize(10)
                .build();

        return KeycloakBuilder.builder()
                .serverUrl(baseUrl)
                .realm("master")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("admin-cli")
                .clientSecret(clientSecret)
                .resteasyClient(client)
                .build();
    }
}
