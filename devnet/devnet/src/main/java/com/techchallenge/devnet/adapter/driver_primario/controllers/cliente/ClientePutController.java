package com.techchallenge.devnet.adapter.driver_primario.controllers.cliente;

import com.techchallenge.devnet.adapter.driver_primario.adapters.IAdapterEntrada;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.ClienteDtoResponse;
import com.techchallenge.devnet.adapter.driver_primario.presenters.IPutPresenter;
import com.techchallenge.devnet.core.application.ports.entrada.cliente.IClienteAtualizarServicePort;
import com.techchallenge.devnet.core.domain.base.exceptions.RetornoDeErro;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "ClientePutControllerAdapter", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/clientes")
public final class ClientePutController implements IClienteControllerPort.PutController {

  @Autowired
  private IAdapterEntrada mapper;

  @Autowired
  private IClienteAtualizarServicePort service;

  @Autowired
  private IPutPresenter presenter;

  @Operation(summary = "Atualizar Cliente", description = "Este recurso destina-se a atualizar pelo identificador exclusivo (ID).")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.", content = {@Content(mediaType = "application/json", array = @ArraySchema(minItems = 1, schema = @Schema(implementation = ClienteDtoResponse.class), uniqueItems = true))}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized: cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<Object> atualizar(
    @Parameter(name = "id", description = "Chave de identificação", example = "22", required = true)
    @PathVariable(name = "id") final Long clienteId,
    @Parameter(name = "ClienteDtoRequest", description = "Estrutura de dados para transporte de informações.", required = true)
    @RequestBody @Valid final ClienteDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterOrigemParaDestino(dto, ClienteModel.class))
      .map(model -> this.service.atualizar(clienteId, model))
      .map(model -> this.mapper.converterOrigemParaDestino(model, ClienteDtoResponse.class))
      .map(this.presenter::put)
      .orElseThrow();
  }
}

