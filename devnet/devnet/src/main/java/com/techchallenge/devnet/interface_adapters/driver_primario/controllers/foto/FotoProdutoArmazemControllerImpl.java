package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.foto;

import com.techchallenge.devnet.application_business_rules.exceptions.RetornoDeErro;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.FotoProdutoNaoEncontradoException;
import com.techchallenge.devnet.application_business_rules.ports.entrada.foto.FotoProdutoBuscarImagemPorIdServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "FotoProdutoArmazemControllerImpl", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/fotos")
public final class FotoProdutoArmazemControllerImpl implements FotoProdutoControllerPort.ConsultarImagemPorIdController {

  private final FotoProdutoBuscarImagemPorIdServicePort service;

  public FotoProdutoArmazemControllerImpl(FotoProdutoBuscarImagemPorIdServicePort service) {
    this.service = service;
  }

  @Operation(summary = "Buscar ImagemPorId do FotoProduto", description = "Este recurso permite consultar Imagem do FotoProduto por diversas propriedades com retorno paginado.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.", content = {@Content(mediaType = "application/json")}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized: cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<InputStreamResource> buscarImagemPorId(@PathVariable(name = "id") final Long id,
                                             @RequestHeader(name = "accept") final String acceptHeader) {
    try {
      var imagemModel = this.service.consultarImagemPorId(id, acceptHeader);

      return ResponseEntity
      .ok()
      .contentType(imagemModel.getMediaTypeFoto())
      .body(imagemModel.getImagem());

    } catch (FotoProdutoNaoEncontradoException fotoProdutoNaoEncontradoException) {

      return ResponseEntity
      .notFound()
      .build();
    }
  }
}

