package com.techchallenge.devnet.core.domain.value_objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techchallenge.devnet.adapter.driver.dtos.response.PagamentoDtoResponse;
import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class CobrancaPagamentoDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private PedidoDtoResponse pedido;

  private PagamentoDtoResponse pagamento;
}

