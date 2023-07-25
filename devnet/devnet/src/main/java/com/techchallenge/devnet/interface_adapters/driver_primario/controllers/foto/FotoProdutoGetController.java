package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.foto;

import com.techchallenge.devnet.frameworks_and_drivers.web.IFotoProdutoControllerPort;
import com.techchallenge.devnet.interface_adapters.driver_primario.adapter_entrada.IAdapterEntrada;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.resposta.FotoProdutoDtoResponse;
import com.techchallenge.devnet.interface_adapters.driver_primario.presenters.IGetPresenter;
import com.techchallenge.devnet.application_business_rules.ports.entrada.foto.IFotoProdutoConsultarPorIdServicePort;
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

@Tag(name = "FotoProdutoGetController", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/fotos")
public final class FotoProdutoGetController implements IFotoProdutoControllerPort.ConsultarPorIdController {

  private final IAdapterEntrada mapper;

  private final IFotoProdutoConsultarPorIdServicePort service;

  private final IGetPresenter presenter;

  public FotoProdutoGetController(IAdapterEntrada mapper,
                                  IFotoProdutoConsultarPorIdServicePort service,
                                  IGetPresenter presenter) {
    this.mapper = mapper;
    this.service = service;
    this.presenter = presenter;
  }

  @Operation(summary = "Pesquisar FotoProduto", description = "Este recurso permite consultar FotoProduto por diversas propriedades com retorno paginado.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.", content = {@Content(mediaType = "application/json")}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized: cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<Object> consultarPorId(
    @Parameter(name = "id", description = "Chave de identificação", example = "22", required = true)
    @PathVariable(name = "id") final Long id) {

    return Optional.of(id)
      .map(codigo -> this.service.consultarPorId(codigo))
      .map(model -> this.mapper.converterOrigemParaDestino(model, FotoProdutoDtoResponse.class))
      .map(this.presenter::get)
      .orElseThrow();
  }
}

