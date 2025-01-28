package com.yaba.springkeycloak.web.query;


import com.yaba.springkeycloak.service.query.KeycloakQueryService;
import com.yaba.springkeycloak.utils.CustomApiResponse;
import com.yaba.springkeycloak.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(KeycloakQueryController.KEYCLOAK_QUERY_ROUTE)
public class KeycloakQueryController {
    public static final String KEYCLOAK_QUERY_ROUTE = "/query/kc";
    private static final Logger log = LoggerFactory.getLogger(KeycloakQueryController.class);

    private final KeycloakQueryService queryService;

    public KeycloakQueryController(KeycloakQueryService queryService) {
        this.queryService = queryService;
    }

    @Operation(
            summary = "Récupérer toutes les utilisateurs",
            description = "Cette API permet de récupérer tous les utilisateurs."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des utilisateurs récupérée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<CustomApiResponse<List<UserRepresentation>>> getAll() {
        log.info("************Request to get all users***********");
        List<UserRepresentation> users = queryService.getUsers();

        return ResponseUtils.buildSuccessResponse(
                users,
                "SUCCESS",
                HttpStatus.OK
        );
    }

//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Catégorie récupérer avec succès"),
//            @ApiResponse(responseCode = "400", description = "Requête invalide"),
//            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
//    })
//    @GetMapping("/{id}")
//    public ResponseEntity<CustomApiResponse<CategoryResponse>> getOneById(
//            @Parameter(description = "Id de la catégorie à récupérer.") @PathVariable UUID id) {
//        CategoryResponse category = queryService.getOne(id);
//        return ResponseUtils.buildSuccessResponse(
//                category,
//                "SUCCESS",
//                HttpStatus.OK
//        );
//    }
}
