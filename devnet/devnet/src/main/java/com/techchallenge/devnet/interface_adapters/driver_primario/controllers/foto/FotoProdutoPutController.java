package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.foto;

import com.techchallenge.devnet.frameworks_and_drivers.web.IFotoProdutoControllerPort;
import com.techchallenge.devnet.interface_adapters.driver_primario.adapter_entrada.IAdapterEntrada;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.FotoProdutoDtoRequest;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.resposta.FotoProdutoDtoResponse;
import com.techchallenge.devnet.interface_adapters.driver_primario.presenters.IPutPresenter;
import com.techchallenge.devnet.application_business_rules.ports.entrada.foto.IFotoProdutoAtualizarServicePort;
import com.techchallenge.devnet.application_business_rules.exceptions.RetornoDeErro;
import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoArquivo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@Tag(name = "FotoProdutoPutController", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/fotos")
public final class FotoProdutoPutController implements IFotoProdutoControllerPort.PutController {

  private final IAdapterEntrada mapper;

  private final IFotoProdutoAtualizarServicePort service;

  private final IPutPresenter presenter;

  public FotoProdutoPutController(IAdapterEntrada mapper,
                                  IFotoProdutoAtualizarServicePort service,
                                  IPutPresenter presenter) {
    this.mapper = mapper;
    this.service = service;
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
  public ResponseEntity<Object> inserirFotoNoProduto(
    @Parameter(name = "id", description = "Chave de identificação", example = "22", required = true)
    @PathVariable(name = "id") final Long produtoId,
    @Valid final FotoProdutoDtoRequest fotoProdutoDtoRequest) {

    return Optional.of(fotoProdutoDtoRequest)
      .map(dtoRequest -> this.mapper.converterOrigemParaDestino(dtoRequest, FotoProdutoArquivo.class))
      .map(arquivo -> {

        try {
          return this.service.inserirFotoNoProduto(produtoId, arquivo);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

      })
      .map(model -> this.mapper.converterOrigemParaDestino(model, FotoProdutoDtoResponse.class))
      .map(this.presenter::put)
      .orElseThrow();
  }
}

