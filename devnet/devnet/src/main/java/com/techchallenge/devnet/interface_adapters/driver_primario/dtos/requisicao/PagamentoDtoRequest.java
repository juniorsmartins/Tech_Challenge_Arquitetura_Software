package com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.FormaPagamentoEnum;
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

