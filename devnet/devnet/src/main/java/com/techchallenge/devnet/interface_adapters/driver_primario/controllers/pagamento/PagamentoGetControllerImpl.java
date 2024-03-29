package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.pagamento;

import com.techchallenge.devnet.application_business_rules.exceptions.RetornoDeErro;
import com.techchallenge.devnet.application_business_rules.ports.entrada.pagamento.PagamentoBuscarQrCodeServicePort;
import com.techchallenge.devnet.application_business_rules.ports.entrada.pagamento.PagamentoPesquisarServicePort;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.PagamentoFiltro;
import com.techchallenge.devnet.interface_adapters.driver_primario.adapter_entrada.AdapterEntrada;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.interface_adapters.driver_primario.filtros.PagamentoFiltroDto;
import com.techchallenge.devnet.interface_adapters.driver_primario.presenters.GetPresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Tag(name = "PagamentoGetControllerAdapter", description = "Adaptador para padronizar a requisição às normalizações da API.")
@RestController
@RequestMapping(path = "/api/v1/pagamentos")
public final class PagamentoGetControllerImpl implements PagamentoControllerPort.PesquisarController,
  PagamentoControllerPort.BuscarQrCodeController {

  private final AdapterEntrada mapper;

  private final PagamentoPesquisarServicePort servicePesquisar;

  private final PagamentoBuscarQrCodeServicePort serviceBuscarQrCode;

  private final GetPresenter presenter;

  public PagamentoGetControllerImpl(AdapterEntrada mapper,
                                    PagamentoPesquisarServicePort servicePesquisar,
                                    PagamentoBuscarQrCodeServicePort serviceBuscarQrCode,
                                    GetPresenter presenter) {
    this.mapper = mapper;
    this.servicePesquisar = servicePesquisar;
    this.serviceBuscarQrCode = serviceBuscarQrCode;
    this.presenter = presenter;
  }

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
  public ResponseEntity<Object> pesquisar(
    @Parameter(name = "PagamentoFiltro", description = "Estrutura de dados usada como filtro de pesquisa.", required = false)
    @Valid final PagamentoFiltroDto filtroDto,
    @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) final Pageable paginacao) {

    return Optional.of(filtroDto)
      .map(dto -> this.mapper.converterOrigemParaDestino(dto, PagamentoFiltro.class))
      .map(parametrosDePesquisa -> this.servicePesquisar.pesquisar(parametrosDePesquisa, paginacao))
      .map(paginaPagamentos -> this.mapper.converterPaginaOrigemParaPaginaDestino(paginaPagamentos, PagamentoDtoResponse.class))
      .map(pagina -> this.presenter.get(pagina))
      .orElseThrow();
  }

  @Operation(summary = "Buscar QRCode", description = "Este recurso permite buscar a imagem do QRCode.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "OK - requisição bem sucedida e com retorno.", content = {@Content(mediaType = "application/json", array = @ArraySchema(minItems = 1, schema = @Schema(implementation = InputStreamResource.class), uniqueItems = true))}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized: cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "404", description = "Not Found - recurso não encontrado no banco de dados.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<InputStreamResource> buscarQrCodePorId(
  @Parameter(name = "id", description = "Chave de identificação", example = "22", required = true)
  @PathVariable(name = "id") final Long id) {

    var response = this.serviceBuscarQrCode.buscarQrCodePorId(id);

    return ResponseEntity
    .ok()
    .contentType(MediaType.IMAGE_PNG)
    .body(response);
  }
}

