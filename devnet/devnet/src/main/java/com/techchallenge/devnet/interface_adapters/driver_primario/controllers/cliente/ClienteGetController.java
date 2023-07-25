package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.cliente;

import com.techchallenge.devnet.frameworks_and_drivers.web.IClienteControllerPort;
import com.techchallenge.devnet.interface_adapters.driver_primario.adapter_entrada.IAdapterEntrada;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.resposta.ClienteDtoResponse;
import com.techchallenge.devnet.interface_adapters.driver_primario.filtros.ClienteFiltroDto;
import com.techchallenge.devnet.interface_adapters.driver_primario.presenters.IGetPresenter;
import com.techchallenge.devnet.application_business_rules.ports.entrada.cliente.IClientePesquisarServicePort;
import com.techchallenge.devnet.application_business_rules.exceptions.RetornoDeErro;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.ClienteFiltro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "ClienteGetController", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/clientes")
public final class ClienteGetController implements IClienteControllerPort.GetController {

  private final IAdapterEntrada mapper;

  private final IClientePesquisarServicePort service;

  private final IGetPresenter presenter;

  public ClienteGetController(IAdapterEntrada mapper,
                              IClientePesquisarServicePort service,
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
  public ResponseEntity<Object> pesquisar(
    @Parameter(name = "ClienteFiltroDto", description = "Estrutura de dados usada como filtro de pesquisa.", required = false)
    final ClienteFiltroDto filtroDto,
    @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) final Pageable paginacao) {

    return Optional.of(filtroDto)
      .map(dto -> this.mapper.converterOrigemParaDestino(dto, ClienteFiltro.class))
      .map(filtro -> this.service.pesquisar(filtro, paginacao))
      .map(paginaClientes -> this.mapper.converterPaginaOrigemParaPaginaDestino(paginaClientes, ClienteDtoResponse.class))
      .map(this.presenter::get)
      .orElseThrow();
  }
}

