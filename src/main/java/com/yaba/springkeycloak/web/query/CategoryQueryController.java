package com.yaba.springkeycloak.web.query;

import com.yaba.springkeycloak.exchange.response.CategoryResponse;
import com.yaba.springkeycloak.service.query.CategoryQueryService;
import com.yaba.springkeycloak.utils.CustomApiResponse;
import com.yaba.springkeycloak.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(CategoryQueryController.CATEGORY_QUERY_ROUTE)
public class CategoryQueryController {
    public static final String CATEGORY_QUERY_ROUTE = "/query/categories";
    private static final Logger log = LoggerFactory.getLogger(CategoryQueryController.class);
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
    public ResponseEntity<CustomApiResponse<Page<CategoryResponse>>> getAll(
            @Parameter(description = "Détails de la pagination (page, taille, etc.)") Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication: {}", authentication);
        Page<CategoryResponse> categories = queryService.getAll(pageable);

        return ResponseUtils.buildSuccessResponse(
                categories,
                "SUCCESS",
                HttpStatus.OK
        );
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie récupérer avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<CategoryResponse>> getOneById(
            @Parameter(description = "Id de la catégorie à récupérer.") @PathVariable UUID id) {
        CategoryResponse category = queryService.getOne(id);
        return ResponseUtils.buildSuccessResponse(
                category,
                "SUCCESS",
                HttpStatus.OK
        );
    }
}
