package com.techchallenge.devnet.adapter.driver_primario.controllers.admin;

import com.techchallenge.devnet.adapter.driver_primario.adapter_entrada.IAdapterEntrada;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.IndicadorDtoResponse;
import com.techchallenge.devnet.adapter.driver_primario.filtros.ClienteFiltroDto;
import com.techchallenge.devnet.adapter.driver_primario.presenters.IGetPresenter;
import com.techchallenge.devnet.core.application.ports.entrada.admin.IAdminBuscarIndicadoresServicePort;
import com.techchallenge.devnet.core.application.exceptions.RetornoDeErro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "AdminGetControllerAdapter", description = "Adaptador para buscar dados administrativos.")
@RestController
@RequestMapping(path = "/api/v1/admin")
public final class AdminGetController implements IAdminControllerPort.GetController {

  private final IAdapterEntrada mapper;

  private final IAdminBuscarIndicadoresServicePort service;

  private final IGetPresenter presenter;

  public AdminGetController(IAdapterEntrada mapper,
                            IAdminBuscarIndicadoresServicePort service,
                            IGetPresenter presenter) {
    this.mapper = mapper;
    this.service = service;
    this.presenter = presenter;
  }

  @Operation(summary = "Pesquisar Cliente", description = "Este recurso permite consultar Cliente por diversas propriedades com retorno paginado.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.", content = {@Content(mediaType = "application/json", array = @ArraySchema(minItems = 1, schema = @Schema(implementation = ClienteFiltroDto.class), uniqueItems = true))}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized: cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<Object> buscarIndicadores() {

    return Optional.of(this.service.buscarIndicadores())
      .map(indicador -> this.mapper.converterOrigemParaDestino(indicador, IndicadorDtoResponse.class))
      .map(this.presenter::get)
      .orElseThrow();
  }
}

