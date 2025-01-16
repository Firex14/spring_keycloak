package com.yaba.springkeycloak.web.cmd;

import com.yaba.springkeycloak.dto.CategoryDto;
import com.yaba.springkeycloak.service.cmd.CategoryCmdService;
import com.yaba.springkeycloak.utils.CustomApiResponse;
import com.yaba.springkeycloak.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryCmdController.CATEGORY_CMD_ROUTE)
public class CategoryCmdController {
    public static final String CATEGORY_CMD_ROUTE = "/cmd/categories";

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
    public ResponseEntity<CustomApiResponse<CategoryDto>> create(
            @Parameter(description = "Détails de la catégorie à créer") @RequestBody CategoryDto request) {
        CategoryDto result = cmdService.save(request);
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
    public ResponseEntity<CustomApiResponse<CategoryDto>> update(
            @Parameter(description = "Détails de la catégorie à mettre à jour") @RequestBody CategoryDto request) {
        CategoryDto result = cmdService.update(request);
        return ResponseUtils.buildSuccessResponse(result, "SUCCESS", HttpStatus.OK);
    }
}
