package com.techchallenge.devnet.adapter.driver_primario.controllers.pagamento;

import com.techchallenge.devnet.adapter.driver_primario.adapters_entrada.IAdapterEntrada;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.pagamento.IPagamentoAtualizarServicePort;
import com.techchallenge.devnet.core.domain.base.exceptions.RetornoDeErro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "PagamentoPutControllerAdapter", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/pagamentos")
public final class PagamentoAtualizarControllerAdapter implements IPagamentoControllerPort.PutController {

  @Autowired
  private IAdapterEntrada mapper;

  @Autowired
  private IPagamentoAtualizarServicePort service;

  @Operation(summary = "Atualizar Cliente", description = "Este recurso destina-se a atualizar pelo identificador exclusivo (ID).")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.", content = {@Content(mediaType = "application/json")}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized: cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<PagamentoDtoResponse> verificarStatusNoGateway(
    @Parameter(name = "id", description = "Chave de identificação", example = "22", required = true)
    @PathVariable(name = "idPedido") final Long idPedido) {

    var response = Optional.of(idPedido)
      .map(id -> this.service.verificarStatusNoGateway(id))
      .map(model -> this.mapper.converterOrigemParaDestino(model, PagamentoDtoResponse.class))
      .orElseThrow();

    return ResponseEntity
      .ok()
      .body(response);
  }
}

