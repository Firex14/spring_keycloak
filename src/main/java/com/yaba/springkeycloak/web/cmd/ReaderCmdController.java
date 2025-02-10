package com.yaba.springkeycloak.web.cmd;

import com.yaba.springkeycloak.exchange.request.reader.ReaderCreationRequest;
import com.yaba.springkeycloak.exchange.response.ReaderResponse;
import com.yaba.springkeycloak.service.cmd.ReaderCmdService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ReaderCmdController.READER_CMD_ROUTE)
public class ReaderCmdController {
    public static final String READER_CMD_ROUTE = "/cmd/readers";
    private static final Logger log = LoggerFactory.getLogger(ReaderCmdController.class);

    private final ReaderCmdService cmdService;

    public ReaderCmdController(ReaderCmdService cmdService) {
        this.cmdService = cmdService;
    }

    @Operation(
            summary = "Enregistrer un nouveau lecteur",
            description = "Cette API permet d'enregistrer un nouveau lecteur en utilisant les informations fournies."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lecteur enregistré avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<CustomApiResponse<ReaderResponse>> create(
            @Parameter(description = "Détails du lecteur à sauvegarder") @RequestBody ReaderCreationRequest request) {
        ReaderResponse result = cmdService.save(request);
        return ResponseUtils.buildSuccessResponse(result, "SUCCESS", HttpStatus.OK);
    }
}
