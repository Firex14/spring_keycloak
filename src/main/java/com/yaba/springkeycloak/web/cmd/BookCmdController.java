package com.yaba.springkeycloak.web.cmd;

import com.yaba.springkeycloak.exchange.request.book.BookCreationRequest;
import com.yaba.springkeycloak.exchange.request.book.BookUpdateRequest;
import com.yaba.springkeycloak.exchange.response.BookResponse;
import com.yaba.springkeycloak.service.cmd.BookCmdService;
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
@RequestMapping(BookCmdController.BOOK_CMD_ROUTE)
public class BookCmdController {

    public static final String BOOK_CMD_ROUTE = "/cmd/books";
    private final BookCmdService cmdService;

    public BookCmdController(BookCmdService cmdService) {
        this.cmdService = cmdService;
    }

    @Operation(
            summary = "Enregistrer un nouveau livre",
            description = "Cette API permet d'enregistrer un nouveau livre en utilisant les informations fournies."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livre enregistré avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<CustomApiResponse<BookResponse>> create(
            @Parameter(description = "Détails du livre à sauvegarder") @RequestBody BookCreationRequest request) {
        BookResponse result = cmdService.save(request);
        return ResponseUtils.buildSuccessResponse(result, "SUCCESS", HttpStatus.OK);
    }

    @Operation(
            summary = "Mettre à jour un livre existant",
            description = "Cette API permet de mettre à jour un livre existant en utilisant les informations fournies."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livre mis à jour avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "404", description = "Livre non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping
    public ResponseEntity<CustomApiResponse<BookResponse>> update(
            @Parameter(description = "Détails du livre à mettre à jour") @RequestBody BookUpdateRequest request) {
        BookResponse result = cmdService.update(request);
        return ResponseUtils.buildSuccessResponse(result, "SUCCESS", HttpStatus.OK);
    }
}
