package com.techchallenge.devnet.core.application.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class RetornoDeErro {

  // https://www.rfc-editor.org/rfc/rfc7807

  private String tipo; // Uma referência de URI para identificar o tipo de problema. Fornece documentação legível (RFC 7807)

  private String titulo; // Um resumo curto e legível do problema (RFC 7807)

  private Integer status; // O código de status HTTP gerado pelo servidor para esta ocorrência do problema (RFC 7807)

  private String detalhe; // Uma explicação legível e específica capaz de ajudar a resolver o problema (RFC 7807)

  private String instancia; // Uma explicação legível por humanos específica para a ocorrência do problema (RFC 7807)

  private Instant dataHoraErro; // Mostra o momento do erro (fora do padrão)

  private List<ParametroInvalido> parametrosInvalidos; // (RFC 7807)

  @Builder
  @Getter
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public static class ParametroInvalido {

    private String anotacaoViolada; // Nome da anotação violada do Bean Validation (fora do padrão)

    private String localDeErro; // Nome do campo em que a anotação foi violada (RFC 7807)

    private String motivo; // explicação sobre o motivo do erro (RFC 7807)
  }
}

