package com.yaba.springkeycloak.web.cmd;

import com.yaba.springkeycloak.exchange.request.category.CategoryCreationRequest;
import com.yaba.springkeycloak.exchange.request.category.CategoryUpdateRequest;
import com.yaba.springkeycloak.exchange.response.CategoryResponse;
import com.yaba.springkeycloak.service.cmd.CategoryCmdService;
import com.yaba.springkeycloak.utils.CustomApiResponse;
import com.yaba.springkeycloak.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryCmdController.CATEGORY_CMD_ROUTE)
public class CategoryCmdController {
    public static final String CATEGORY_CMD_ROUTE = "/cmd/categories";
    private static final Logger log = LoggerFactory.getLogger(CategoryCmdController.class);


    private final CategoryCmdService cmdService;

    public CategoryCmdController(CategoryCmdService cmdService) {
        this.cmdService = cmdService;
    }

    @Operation(
            summary = "Créer une nouvelle catégorie",
            description = "Cette API permet de créer une nouvelle catégorie en utilisant les informations fournies."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<CustomApiResponse<CategoryResponse>> create(
            @Parameter(description = "Détails de la catégorie à créer") @RequestBody CategoryCreationRequest request) {
        log.info("*************** Request to save Category ***************");
        CategoryResponse result = cmdService.save(request);
        return ResponseUtils.buildSuccessResponse(result, "SUCCESS", HttpStatus.OK);
    }

    @Operation(
            summary = "Mettre à jour une catégorie existante",
            description = "Cette API permet de mettre à jour une catégorie existante en utilisant les informations fournies."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie mise à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping
    public ResponseEntity<CustomApiResponse<CategoryResponse>> update(
            @Parameter(description = "Détails de la catégorie à mettre à jour") @RequestBody CategoryUpdateRequest request) {
        log.info("**************** Request to update Category ************");
        CategoryResponse result = cmdService.update(request);
        return ResponseUtils.buildSuccessResponse(result, "SUCCESS", HttpStatus.OK);
    }
}
