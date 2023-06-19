package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.core.application.use_case.IPagamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PagamentoPostController", description = "Adaptador para efetuar pagamento.")
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/pagamentos")
public final class PagamentoPostController implements IPagamentoController.PostController {

  @Autowired
  private IPagamentoService.PostService pagamentoPostService;

//  @Operation(summary = "Cadastrar Pagamento", description = "Este recurso destina-se a cadastrar.")
//  @ApiResponses(value = {
//    @ApiResponse(responseCode = "201", description = "Created - novo recurso criado com sucesso!", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PagamentoDtoRequest.class))}),
//    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
//    @ApiResponse(responseCode = "401", description = "Unauthorized - cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
//    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
//    @ApiResponse(responseCode = "409", description = "Conflict - requisição não concluída por conflito de estado do recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
//    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
//  })
//  @Override
//  public SolicitacaoDePagamentoDto solicitarPagamento(
//    @Parameter(name = "PagamentoDtoRequest", description = "Estrutura de dados para transporte de informações de entrada.", required = true)
//    @RequestBody @Valid final Pedido pedido) {
//
//    System.out.println("\n\n---------- PagamentoPostController -> ----------\n\n");
//
//    var response = Optional.of(pedido)
//      .map(this.pagamentoPostService::solicitarPagamento)
//      .orElseThrow();
//
//    return response;
//  }
}

