package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.request.PagamentoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PagamentoDtoResponse;
import com.techchallenge.devnet.core.domain.base.exceptions.RetornoDeErro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "PagamentoPostController", description = "Adaptador para efetuar pagamento.")
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/pagamentos")
public final class PagamentoPostController implements IPagamentoController.PostController {

  @Operation(summary = "Cadastrar Produto", description = "Este recurso destina-se a cadastrar.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Created - novo recurso criado com sucesso!", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PagamentoDtoRequest.class))}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized - cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "409", description = "Conflict - requisição não concluída por conflito de estado do recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<PagamentoDtoResponse> cadastrar(
    @Parameter(name = "PagamentoDtoRequest", description = "Estrutura de dados para transporte de informações de entrada.", required = true)
    @RequestBody @Valid final PagamentoDtoRequest dtoRequest, final UriComponentsBuilder uriComponentsBuilder) {

    System.out.println("\n\n---------- Pagamento Efetuado ----------\n\n");

    var response = PagamentoDtoResponse.builder()
      .pago(true)
      .build();

    return ResponseEntity
      .ok()
      .body(response);
  }
}

