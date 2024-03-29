package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.cliente;

import com.techchallenge.devnet.application_business_rules.exceptions.RetornoDeErro;
import com.techchallenge.devnet.application_business_rules.ports.entrada.cliente.ClienteApagarServicePort;
import com.techchallenge.devnet.interface_adapters.driver_primario.presenters.DeletePresenter;
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

@Tag(name = "ClienteDeleteControllerImpl", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/clientes")
public final class ClienteDeleteControllerImpl implements ClienteControllerPort.DeleteController {

  private final ClienteApagarServicePort service;

  private final DeletePresenter presenter;

  public ClienteDeleteControllerImpl(ClienteApagarServicePort service,
                                     DeletePresenter presenter) {
    this.service = service;
    this.presenter = presenter;
  }

  @Operation(summary = "Deletar Cliente", description = "Este recurso destina-se a apagar pelo identificador exclusivo (ID).")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "No Content - requisição bem sucedida e sem retorno.", content = {@Content(mediaType = "application/json")}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized: cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<Object> deletarPorId(
    @Parameter(name = "id", description = "Chave de identificação", example = "22", required = true)
    @PathVariable(name = "id") final Long clienteId) {

    this.service.deletar(clienteId);
    return this.presenter.delete();
  }
}

