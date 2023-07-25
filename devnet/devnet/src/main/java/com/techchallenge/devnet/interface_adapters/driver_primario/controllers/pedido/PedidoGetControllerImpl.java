package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.pedido;

import com.techchallenge.devnet.frameworks_and_drivers.web.PedidoControllerPort;
import com.techchallenge.devnet.interface_adapters.driver_primario.adapter_entrada.AdapterEntrada;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.resposta.PedidoDtoResponse;
import com.techchallenge.devnet.interface_adapters.driver_primario.filtros.PedidoFiltroDto;
import com.techchallenge.devnet.interface_adapters.driver_primario.presenters.GetPresenter;
import com.techchallenge.devnet.application_business_rules.ports.entrada.pedido.IPedidoListarOrdenadoServicePort;
import com.techchallenge.devnet.application_business_rules.ports.entrada.pedido.IPedidoPesquisarServicePort;
import com.techchallenge.devnet.application_business_rules.exceptions.RetornoDeErro;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.PedidoFiltro;
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

@Tag(name = "PedidoGetController", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/pedidos")
public final class PedidoGetControllerImpl implements PedidoControllerPort.GetController {

  private final AdapterEntrada mapper;

  private final IPedidoPesquisarServicePort servicePesquisar;

  private final IPedidoListarOrdenadoServicePort serviceListar;

  private final GetPresenter presenter;

  public PedidoGetControllerImpl(AdapterEntrada mapper,
                                 IPedidoPesquisarServicePort servicePesquisar,
                                 IPedidoListarOrdenadoServicePort serviceListar,
                                 GetPresenter presenter) {
    this.mapper = mapper;
    this.servicePesquisar = servicePesquisar;
    this.serviceListar = serviceListar;
    this.presenter = presenter;
  }

  @Operation(summary = "Pesquisar Pedido", description = "Este recurso permite consultar Pedido por diversas propriedades com retorno paginado.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.", content = {@Content(mediaType = "application/json", array = @ArraySchema(minItems = 1, schema = @Schema(implementation = PedidoFiltro.class), uniqueItems = true))}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized: cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<Object> pesquisar(
    @Parameter(name = "PedidoFiltro", description = "Estrutura de dados usada como filtro de pesquisa.", required = false)
    final PedidoFiltroDto filtroDto,
    @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) final Pageable paginacao) {

    return Optional.of(filtroDto)
      .map(dto -> this.mapper.converterOrigemParaDestino(dto, PedidoFiltro.class))
      .map(parametrosDePesquisa -> this.servicePesquisar.pesquisar(parametrosDePesquisa, paginacao))
      .map(paginaPedidos -> this.mapper.converterPaginaOrigemParaPaginaDestino(paginaPedidos, PedidoDtoResponse.class))
      .map(this.presenter::get)
      .orElseThrow();
  }

  @Operation(summary = "Listar Pedido Ordenado", description = "Este recurso permite listar Pedido ordenado por StatusPedido e por dataHora do recebimento.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.", content = {@Content(mediaType = "application/json", array = @ArraySchema(minItems = 1, schema = @Schema(implementation = PedidoFiltro.class), uniqueItems = true))}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized: cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<Object> listarOrdenadoPorStatusAndDataHoraCadastro() {

    return Optional.of(this.serviceListar.listarOrdenadoPorStatusAndDataHoraCadastro())
      .map(models -> this.mapper.converterListaOrigemParaListaDestino(models, PedidoDtoResponse.class))
      .map(this.presenter::get)
      .orElseThrow();
  }
}

