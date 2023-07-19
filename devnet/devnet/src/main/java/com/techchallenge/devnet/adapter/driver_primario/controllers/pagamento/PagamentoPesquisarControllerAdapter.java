package com.techchallenge.devnet.adapter.driver_primario.controllers.pagamento;

import com.techchallenge.devnet.adapter.driver_primario.adapters_entrada.IAdapterEntrada;
import com.techchallenge.devnet.adapter.driver_primario.filtros.PagamentoFiltroDto;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.pagamento.IPagamentoPesquisarServicePort;
import com.techchallenge.devnet.core.domain.base.exceptions.RetornoDeErro;
import com.techchallenge.devnet.core.domain.objects.filtros.PagamentoFiltro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "PagamentoGetControllerAdapter", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/pagamentos")
public final class PagamentoPesquisarControllerAdapter implements IPagamentoControllerPort.PesquisarController {

  @Autowired
  private IAdapterEntrada mapper;

  @Autowired
  private IPagamentoPesquisarServicePort service;

  @Operation(summary = "Pesquisar Pagamento", description = "Este recurso permite consultar por diversas propriedades com retorno paginado.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.", content = {@Content(mediaType = "application/json", array = @ArraySchema(minItems = 1, schema = @Schema(implementation = PagamentoDtoResponse.class), uniqueItems = true))}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized: cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
})
  @Override
  public ResponseEntity<Page<PagamentoDtoResponse>> pesquisar(
    @Parameter(name = "PagamentoFiltro", description = "Estrutura de dados usada como filtro de pesquisa.", required = false)
    final PagamentoFiltroDto filtroDto,
    @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) final Pageable paginacao) {

    var response = Optional.of(filtroDto)
      .map(dto -> this.mapper.converterOrigemParaDestino(dto, PagamentoFiltro.class))
      .map(parametrosDePesquisa -> this.service.pesquisar(parametrosDePesquisa, paginacao))
      .map(paginaPagamentos -> this.mapper.converterPaginaOrigemParaPaginaDestino(paginaPagamentos, PagamentoDtoResponse.class))
      .orElseThrow();

    return ResponseEntity
      .ok()
      .body(response);
  }
}

