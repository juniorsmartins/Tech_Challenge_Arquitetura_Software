package com.techchallenge.devnet.adapter.driver_primario.controllers.produto;

import com.techchallenge.devnet.adapter.driver_primario.adapters.IAdapterEntrada;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.ProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.produto.IProdutoCadastrarServicePort;
import com.techchallenge.devnet.core.domain.base.exceptions.RetornoDeErro;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
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

import java.net.URI;
import java.util.Optional;

@Tag(name = "ProdutoPostControllerAdapter", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/produtos")
public final class ProdutoCadastrarControllerAdapter implements IProdutoControllerPort.PostController {

  @Autowired
  private IAdapterEntrada mapper;

  @Autowired
  private IProdutoCadastrarServicePort service;

  @Operation(summary = "Cadastrar Produto", description = "Este recurso destina-se a cadastrar.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Created - novo recurso criado com sucesso!", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoDtoRequest.class))}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized - cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "409", description = "Conflict - requisição não concluída por conflito de estado do recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<ProdutoDtoResponse> cadastrar(
    @Parameter(name = "ProdutoDtoRequest", description = "Estrutura de dados para transporte de informações de entrada.", required = true)
    @RequestBody @Valid final ProdutoDtoRequest dtoRequest) {

    var response = Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterOrigemParaDestino(dto, ProdutoModel.class))
      .map(this.service::cadastrar)
      .map(model -> this.mapper.converterOrigemParaDestino(model, ProdutoDtoResponse.class))
      .orElseThrow();

    return ResponseEntity
      .created(URI.create("/api/v1/produtos" + response.getId()))
      .body(response);
  }
}

