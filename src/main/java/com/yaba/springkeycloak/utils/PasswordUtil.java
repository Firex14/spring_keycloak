package com.yaba.springkeycloak.utils;

import org.keycloak.representations.idm.CredentialRepresentation;

import java.security.SecureRandom;
import java.util.Calendar;

public class PasswordUtil {
    public static CredentialRepresentation createCredential() {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(true); // Indiquer que le mot de passe est temporaire
        credential.setType(CredentialRepresentation.PASSWORD);

        // Génération du mot de passe
        String password = generatePassword();
        credential.setValue(password);

        return credential;
    }

    private static String generatePassword() {
        SecureRandom random = new SecureRandom();

        String randomString = generateRandomString(random);
        String currentYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        String specialCharacters = "@#";

        return specialCharacters + randomString + currentYear + specialCharacters;
    }

    private static String generateRandomString(SecureRandom random) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            builder.append(characters.charAt(index));
        }

        return builder.toString();
    }
}
