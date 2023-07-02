package com.techchallenge.devnet.core.domain.value_objects.filtros;

import com.techchallenge.devnet.core.domain.models.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.core.domain.models.enums.StatusPedidoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PedidoFiltro {

  private String id;

  private StatusPedidoEnum statusPedido;

  private String cliente;

  private FormaPagamentoEnum formaPagamento;
}

