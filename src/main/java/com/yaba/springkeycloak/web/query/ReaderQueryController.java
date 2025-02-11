package com.yaba.springkeycloak.web.query;

import com.yaba.springkeycloak.exchange.response.ReaderResponse;
import com.yaba.springkeycloak.service.query.ReaderQueryService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping(ReaderQueryController.READER_QUERY_ROUTE)
public class ReaderQueryController {
    public static final String READER_QUERY_ROUTE = "/query/readers";
    private static final Logger log = LoggerFactory.getLogger(ReaderQueryController.class);
    private final ReaderQueryService queryService;

    public ReaderQueryController(ReaderQueryService queryService) {
        this.queryService = queryService;
    }


    @Operation(
            summary = "Récupérer toutes les Lecteurs",
            description = "Cette API permet de récupérer toutes les Lecteurs avec un support de pagination."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des Lecteurs récupérée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<CustomApiResponse<Page<ReaderResponse>>> getAll(
            @Parameter(description = "Détails de la pagination (page, taille, etc.)") Pageable pageable) {
        log.info("Request to get All Readers");

        Page<ReaderResponse> readers = queryService.getAll(pageable);

        return ResponseUtils.buildSuccessResponse(
                readers,
                "SUCCESS",
                HttpStatus.OK
        );
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lecteur récupérer avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ReaderResponse>> getOneById(
            @Parameter(description = "Id du lecteur à récupérer.") @PathVariable UUID id) {
        ReaderResponse response = queryService.getOne(id);
        return ResponseUtils.buildSuccessResponse(
                response,
                "SUCCESS",
                HttpStatus.OK
        );
    }
}
