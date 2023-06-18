package com.techchallenge.devnet.adapter.driver.dtos.requisicao;

import com.techchallenge.devnet.core.domain.entities.enums.FormaPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class PagamentoDtoRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private FormaPagamentoEnum formaPagamento;

  private BigDecimal precoTotal;
}

