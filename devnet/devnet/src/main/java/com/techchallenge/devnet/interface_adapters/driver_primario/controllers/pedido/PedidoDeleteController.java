package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.pedido;

import com.techchallenge.devnet.frameworks_and_drivers.web.IPedidoControllerPort;
import com.techchallenge.devnet.interface_adapters.driver_primario.presenters.IDeletePresenter;
import com.techchallenge.devnet.application_business_rules.ports.entrada.pedido.IPedidoApagarServicePort;
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

@Tag(name = "PedidoDeleteController", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/pedidos")
public final class PedidoDeleteController implements IPedidoControllerPort.DeleteController {

  private final IPedidoApagarServicePort service;

  private final IDeletePresenter presenter;

  public PedidoDeleteController(IPedidoApagarServicePort service,
                                IDeletePresenter presenter) {
    this.service = service;
    this.presenter = presenter;
  }

  @Operation(summary = "Cancelar Pedido", description = "Este recurso destina-se a apagar pelo identificador exclusivo (ID).")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "No Content - requisição bem sucedida e sem retorno.", content = {@Content(mediaType = "application/json")}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized: cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<Object> cancelarPorId(
    @Parameter(name = "id", description = "Chave de identificação", example = "22", required = true)
    @PathVariable(name = "id") final Long pedidoId) {

    this.service.cancelarPorId(pedidoId);
    return this.presenter.delete();
  }
}

