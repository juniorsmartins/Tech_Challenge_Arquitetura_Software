package com.techchallenge.devnet.adapter.driver_primario.dtos.resposta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagamentoDtoResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private StatusPagamentoEnum statusPagamento;

  private String nomeImagemQRCode;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;
}

