package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.copa;

import com.techchallenge.devnet.interface_adapters.driver_primario.adapter_entrada.AdapterEntrada;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.resposta.PedidoDtoResponse;
import com.techchallenge.devnet.interface_adapters.driver_primario.presenters.PutPresenter;
import com.techchallenge.devnet.application_business_rules.ports.entrada.copa.CopaPedidoFinalizadoServicePort;
import com.techchallenge.devnet.application_business_rules.ports.entrada.copa.CopaPedidoProntoServicePort;
import com.techchallenge.devnet.application_business_rules.exceptions.RetornoDeErro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "CopaPutController", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/copa")
public final class CopaPutControllerImpl implements CopaControllerPort.PutController {

  private final AdapterEntrada mapper;

  private final CopaPedidoProntoServicePort servicePronto;

  private final CopaPedidoFinalizadoServicePort serviceFinalizado;

  private final PutPresenter presenter;

  public CopaPutControllerImpl(AdapterEntrada mapper,
                               CopaPedidoProntoServicePort servicePronto,
                               CopaPedidoFinalizadoServicePort serviceFinalizado,
                               PutPresenter presenter) {
    this.mapper = mapper;
    this.servicePronto = servicePronto;
    this.serviceFinalizado = serviceFinalizado;
    this.presenter = presenter;
  }

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
  public ResponseEntity<Object> confirmarPedidoPronto(
    @Parameter(name = "id", description = "Chave de identificação", example = "22", required = true)
    @PathVariable(name = "idPedido") final Long idPedido) {

    return Optional.of(idPedido)
      .map(id -> this.servicePronto.confirmarPedidoPronto(id))
      .map(model -> this.mapper.converterOrigemParaDestino(model, PedidoDtoResponse.class))
      .map(this.presenter::put)
      .orElseThrow();
  }

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
  public ResponseEntity<Object> confirmarPedidoFinalizado(
    @Parameter(name = "id", description = "Chave de identificação", example = "22", required = true)
    @PathVariable(name = "idPedido") final Long idPedido) {

    return Optional.of(idPedido)
      .map(id -> this.serviceFinalizado.confirmarPedidoFinalizado(id))
      .map(model -> this.mapper.converterOrigemParaDestino(model, PedidoDtoResponse.class))
      .map(this.presenter::put)
      .orElseThrow();
  }
}

