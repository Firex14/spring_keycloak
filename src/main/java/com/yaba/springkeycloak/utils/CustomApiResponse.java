package com.yaba.springkeycloak.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Schema(description = "Réponse API personnalisée encapsulant le résultat de l'opération")
public class CustomApiResponse<T> {

    @Schema(description = "Indicateur de succès de la requête", example = "true")
    private boolean success;

    @Schema(description = "Données retournées par l'API", implementation = Object.class)
    private T data;

    @Schema(description = "Message d'information ou d'erreur relatif à l'opération", example = "Opération réussie")
    private String message;

    @Schema(description = "Code de statut HTTP associé à la réponse", example = "200")
    private int status;

    @Schema(description = "Liste des erreurs rencontrées (si présentes)")
    private Map<String, String> errors;

    public CustomApiResponse(boolean success, T data, String message, int status) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.status = status;
        this.errors = new HashMap<>();
    }

}
