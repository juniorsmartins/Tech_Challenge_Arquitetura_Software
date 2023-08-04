package com.techchallenge.devnet.enterprise_business_rules.objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Indicador implements Serializable {

  private static final long serialVersionUID = 1L;

  private BigDecimal totalPagamentoEmAberto;

  private BigDecimal totalPagamentoPago;

  private BigDecimal totalPagamentoCancelado;

  private OffsetDateTime dataHoraDadosCriados = OffsetDateTime.now();
}

