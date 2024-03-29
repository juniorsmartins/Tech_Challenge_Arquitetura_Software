package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.email;

import com.techchallenge.devnet.interface_adapters.driver_primario.adapter_entrada.AdapterEntrada;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.EmailDtoRequest;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.resposta.EmailDtoResponse;
import com.techchallenge.devnet.interface_adapters.driver_primario.presenters.PostPresenter;
import com.techchallenge.devnet.application_business_rules.ports.entrada.email.EmailEnviarServicePort;
import com.techchallenge.devnet.application_business_rules.exceptions.RetornoDeErro;
import com.techchallenge.devnet.enterprise_business_rules.models.EmailModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Tag(name = "EmailPostControllerAdapter", description = "Adaptador para enviar recurso Email.")
@RestController
@RequestMapping(path = "/api/v1/emails")
public final class EmailPostControllerImpl implements EmailControllerPort.PostController {

  private final AdapterEntrada mapper;

  private final EmailEnviarServicePort service;

  private final PostPresenter presenter;

  public EmailPostControllerImpl(AdapterEntrada mapper,
                                 EmailEnviarServicePort service,
                                 PostPresenter presenter) {
    this.mapper = mapper;
    this.service = service;
    this.presenter = presenter;
  }

  @Operation(summary = "Enviar Email", description = "Este recurso destina-se a enviar Email.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Created - novo recurso criado com sucesso!", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = EmailDtoResponse.class))}),
    @ApiResponse(responseCode = "400", description = "Bad Request - requisição mal feita.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthorized - cliente não autenticado.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden - cliente autenticado, mas sem autorização para acessar recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "409", description = "Conflict - requisição não concluída por conflito de estado do recurso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error - situação inesperada no servidor.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RetornoDeErro.class))})
  })
  @Override
  public ResponseEntity<Object> enviar(
    @Parameter(name = "EmailDtoRequest", description = "Estrutura de dados para transporte de informações de entrada.", required = true)
    @RequestBody @Valid final EmailDtoRequest dtoRequest, final UriComponentsBuilder uriComponentsBuilder) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterOrigemParaDestino(dto, EmailModel.class))
      .map(this.service::enviar)
      .map(model -> this.mapper.converterOrigemParaDestino(model, EmailDtoResponse.class))
      .map(dto -> this.presenter.post(dto.getId(), dto))
      .orElseThrow();
  }
}

