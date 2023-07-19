package com.techchallenge.devnet.adapter.driver_primario.controllers.cliente;

import com.techchallenge.devnet.adapter.driver_primario.adapters_entrada.IAdapterEntrada;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.ClienteDtoResponse;
import com.techchallenge.devnet.adapter.driver_primario.presenters.IPostPresenter;
import com.techchallenge.devnet.core.application.ports.entrada.cliente.IClienteCadastrarServicePort;
import com.techchallenge.devnet.core.domain.base.exceptions.RetornoDeErro;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "ClientePostControllerAdapter", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/clientes")
public final class ClientePostController implements IClienteControllerPort.PostController {

  @Autowired
  private IAdapterEntrada mapper;

  @Autowired
  private IClienteCadastrarServicePort service;

  @Autowired
  private IPostPresenter presenter;

  @Operation(summary = "Cadastrar Cliente", description = "Este recurso destina-se a cadastrar.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Created - novo recurso criado com sucesso!", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDtoRequest.class))}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized - cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "409", description = "Conflict - requisição não concluída por conflito de estado do recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<Object> cadastrar(
    @Parameter(name = "ClienteDtoRequest", description = "Estrutura de dados para transporte de informações de entrada.", required = true)
    @RequestBody @Valid final ClienteDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterOrigemParaDestino(dto, ClienteModel.class))
      .map(this.service::cadastrar)
      .map(model -> this.mapper.converterOrigemParaDestino(model, ClienteDtoResponse.class))
      .map(dto -> this.presenter.post(dto.getId(), dto))
      .orElseThrow();
  }
}

