package com.yaba.springkeycloak.web.cmd;


import com.yaba.springkeycloak.exchange.request.user.UserCreateRequest;
import com.yaba.springkeycloak.service.cmd.KeycloakCmdService;
import com.yaba.springkeycloak.utils.CustomApiResponse;
import com.yaba.springkeycloak.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(KeycloakCmdController.KEYCLOAK_CMD_ROUTE)
public class KeycloakCmdController {
    public static final String KEYCLOAK_CMD_ROUTE = "/cmd/kc";
    private static final Logger log = LoggerFactory.getLogger(KeycloakCmdController.class);

    private final KeycloakCmdService cmdService;

    public KeycloakCmdController(KeycloakCmdService cmdService) {
        this.cmdService = cmdService;
    }

    @Operation(
            summary = "Enregistrer un nouveau Bibliothécaire",
            description = "Cette API permet d'enregistrer un nouveau Bibliothécaire en utilisant les informations fournies."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bibliothécaire enregistré avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<CustomApiResponse<UserRepresentation>> create(
            @Parameter(description = "Détails du Bibliothécaire à sauvegarder") @RequestBody UserCreateRequest request) {
        log.info("********* Request to create a new user *********");
        UserRepresentation result = cmdService.save(request);
        return ResponseUtils.buildSuccessResponse(result, "SUCCESS", HttpStatus.OK);
    }

}
