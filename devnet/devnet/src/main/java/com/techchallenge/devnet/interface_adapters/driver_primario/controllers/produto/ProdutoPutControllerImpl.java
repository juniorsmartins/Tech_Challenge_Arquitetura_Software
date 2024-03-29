package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.produto;

import com.techchallenge.devnet.application_business_rules.exceptions.RetornoDeErro;
import com.techchallenge.devnet.application_business_rules.ports.entrada.produto.ProdutoAtualizarServicePort;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import com.techchallenge.devnet.interface_adapters.driver_primario.adapter_entrada.AdapterEntrada;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.ProdutoDtoRequest;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.resposta.ProdutoDtoResponse;
import com.techchallenge.devnet.interface_adapters.driver_primario.presenters.PutPresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "ProdutoPutControllerImpl", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/produtos")
public final class ProdutoPutControllerImpl implements ProdutoControllerPort.PutController {

  private final AdapterEntrada mapper;

  private final ProdutoAtualizarServicePort service;

  private final PutPresenter presenter;

  public ProdutoPutControllerImpl(AdapterEntrada mapper,
                                  ProdutoAtualizarServicePort service,
                                  PutPresenter presenter) {
    this.mapper = mapper;
    this.service = service;
    this.presenter = presenter;
  }

  @Operation(summary = "Atualizar Produto", description = "Este recurso destina-se a atualizar pelo identificador exclusivo (ID).")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.", content = {@Content(mediaType = "application/json", array = @ArraySchema(minItems = 1, schema = @Schema(implementation = ProdutoDtoResponse.class), uniqueItems = true))}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized: cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<Object> atualizar(
    @Parameter(name = "id", description = "Chave de identificação", example = "22", required = true)
    @PathVariable(name = "id") final Long produtoId,
    @Parameter(name = "ProdutoDtoRequest", description = "Estrutura de dados para transporte de informações.", required = true)
    @RequestBody @Valid final ProdutoDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterOrigemParaDestino(dto, ProdutoModel.class))
      .map(model -> this.service.atualizar(produtoId, model))
      .map(model -> this.mapper.converterOrigemParaDestino(model, ProdutoDtoResponse.class))
      .map(this.presenter::put)
      .orElseThrow();
  }
}

