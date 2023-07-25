package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.produto;

import com.techchallenge.devnet.frameworks_and_drivers.web.IProdutoControllerPort;
import com.techchallenge.devnet.interface_adapters.driver_primario.adapter_entrada.IAdapterEntrada;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.ProdutoDtoRequest;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.resposta.ProdutoDtoResponse;
import com.techchallenge.devnet.interface_adapters.driver_primario.presenters.IPostPresenter;
import com.techchallenge.devnet.application_business_rules.ports.entrada.produto.IProdutoCadastrarServicePort;
import com.techchallenge.devnet.application_business_rules.exceptions.RetornoDeErro;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "ProdutoPostController", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/produtos")
public final class ProdutoPostController implements IProdutoControllerPort.PostController {

  private final IAdapterEntrada mapper;

  private final IProdutoCadastrarServicePort service;

  private final IPostPresenter presenter;

  public ProdutoPostController(IAdapterEntrada mapper,
                               IProdutoCadastrarServicePort service,
                               IPostPresenter presenter) {
    this.mapper = mapper;
    this.service = service;
    this.presenter = presenter;
  }

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
  public ResponseEntity<Object> cadastrar(
    @Parameter(name = "ProdutoDtoRequest", description = "Estrutura de dados para transporte de informações de entrada.", required = true)
    @RequestBody @Valid final ProdutoDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterOrigemParaDestino(dto, ProdutoModel.class))
      .map(this.service::cadastrar)
      .map(model -> this.mapper.converterOrigemParaDestino(model, ProdutoDtoResponse.class))
      .map(dto -> this.presenter.post(dto.getId(), dto))
      .orElseThrow();
  }
}

