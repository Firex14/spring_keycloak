package com.yaba.springkeycloak.web.query;

import com.yaba.springkeycloak.dto.CategoryDto;
import com.yaba.springkeycloak.service.query.CategoryQueryService;
import com.yaba.springkeycloak.utils.CustomApiResponse;
import com.yaba.springkeycloak.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CategoryQueryController.CATEGORY_QUERY_ROUTE)
public class CategoryQueryController {
    public static final String CATEGORY_QUERY_ROUTE = "/query/categories";
    private final CategoryQueryService queryService;

    public CategoryQueryController(CategoryQueryService queryService) {
        this.queryService = queryService;
    }

    @Operation(
            summary = "Récupérer toutes les catégories",
            description = "Cette API permet de récupérer toutes les catégories avec un support de pagination."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des catégories récupérée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<CustomApiResponse<Page<CategoryDto>>> getAll(
            @Parameter(description = "Détails de la pagination (page, taille, etc.)") Pageable pageable) {
        Page<CategoryDto> categories = queryService.getAll(pageable);
        return ResponseUtils.buildSuccessResponse(
                categories,
                "Liste des catégories récupérée avec succès",
                HttpStatus.OK
        );
    }

}
