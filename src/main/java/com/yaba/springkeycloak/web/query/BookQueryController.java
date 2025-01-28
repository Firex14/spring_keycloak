package com.yaba.springkeycloak.web.query;

import com.yaba.springkeycloak.exchange.response.BookResponse;
import com.yaba.springkeycloak.service.query.BookQueryService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(BookQueryController.BOOK_QUERY_ROUTE)
public class BookQueryController {
    public static final String BOOK_QUERY_ROUTE = "/query/books";

    private final BookQueryService queryService;

    public BookQueryController(BookQueryService queryService) {
        this.queryService = queryService;
    }

    @Operation(
            summary = "Récupérer tous les livres",
            description = "Cette API permet de récupérer tous les livres avec un support de pagination."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des livres récupérée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<CustomApiResponse<Page<BookResponse>>> getAll(
            @Parameter(description = "Détails de la pagination (page, taille, etc.)") Pageable pageable) {
        Page<BookResponse> books = queryService.getAll(pageable);
        return ResponseUtils.buildSuccessResponse(
                books,
                "SUCCESS",
                HttpStatus.OK
        );
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livre récupérer avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<BookResponse>> getOneById(
            @Parameter(description = "Id du livre à récupérer.") @PathVariable UUID id) {
        BookResponse book = queryService.getOne(id);
        return ResponseUtils.buildSuccessResponse(
                book,
                "SUCCESS",
                HttpStatus.OK
        );
    }
}
