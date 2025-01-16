package com.yaba.springkeycloak.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseUtils {

    // Constructeur privé pour empêcher l'instanciation
    private ResponseUtils() { }

    /**
     * Crée une réponse de succès avec des données.
     *
     * @param data    Les données à retourner.
     * @param message Message décrivant le succès.
     * @param status  Le statut HTTP à retourner.
     * @param <T>     Type des données retournées.
     * @return Une instance de ResponseEntity contenant ApiResponse.
     */
    public static <T> ResponseEntity<CustomApiResponse<T>> buildSuccessResponse(
            T data, String message, HttpStatus status) {
        CustomApiResponse<T> customApiResponse = new CustomApiResponse<>(true, data, message, status.value());
        return ResponseEntity.status(status).body(customApiResponse);
    }

    /**
     * Crée une réponse d'erreur avec un message et des détails d'erreur.
     *
     * @param errorMessage Le message d'erreur principal.
     * @param errors       Détails des erreurs spécifiques sous forme de clé-valeur.
     * @param status       Le statut HTTP à retourner.
     * @param <T>          Type générique (peut être ignoré car la réponse est vide).
     * @return Une instance de ResponseEntity contenant ApiResponse.
     */
    public static <T> ResponseEntity<CustomApiResponse<T>> buildErrorResponse(
            String errorMessage, Map<String, String> errors, HttpStatus status) {
        if (errors == null) {
            errors = new HashMap<>();
        }

        CustomApiResponse<T> customApiResponse = new CustomApiResponse<>(false, null, errorMessage, status.value());
        customApiResponse.setErrors(errors);
        return ResponseEntity.status(status).body(customApiResponse);
    }

    /**
     * Crée une réponse vide avec des en-têtes et un statut HTTP.
     *
     * @param httpStatus Le statut HTTP à retourner.
     * @return Une instance de ResponseEntity vide.
     */
    public static ResponseEntity<Void> buildVoidResponse(HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(null);
    }
}
