package com.yaba.springkeycloak.web.cmd;

import com.yaba.springkeycloak.exchange.request.bookLoan.BookLoanCreationRequest;
import com.yaba.springkeycloak.service.cmd.BookLoanCmdService;
import com.yaba.springkeycloak.utils.CustomApiResponse;
import com.yaba.springkeycloak.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(BookLoanCmdController.LOAN_CMD_ROUTE)
public class BookLoanCmdController {
    public static final String LOAN_CMD_ROUTE = "/cmd/loans";

    private final BookLoanCmdService cmdService;

    public BookLoanCmdController(BookLoanCmdService cmdService) {
        this.cmdService = cmdService;
    }

    @Operation(
            summary = "Enregistrer un nouveau Prêt",
            description = "Cette API permet d'enregistrer un nouveau Prêt en utilisant les informations fournies."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prêt enregistré avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<CustomApiResponse<Void>> create(
            @Parameter(description = "Détails du Prêt à sauvegarder") @RequestBody BookLoanCreationRequest request) {
         cmdService.loanBook(request);
        return ResponseUtils.buildVoidResponse("Prêt effectué avec succès.",HttpStatus.OK);
    }


    @Operation(
            summary = "Retourner un livre",
            description = "Cette API permet de retourner un livre en utilisant les informations fournies."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livre retourné avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping(path = "/{loanId}")
    public ResponseEntity<CustomApiResponse<Void>> update(
            @Parameter(description = "Détails du prêt à rendre") @PathVariable UUID loanId) {
        cmdService.returnBook(loanId);
        return ResponseUtils.buildVoidResponse("Prêt rendu avec succès.",HttpStatus.OK);
    }
}
