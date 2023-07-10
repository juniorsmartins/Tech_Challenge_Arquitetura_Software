package com.techchallenge.devnet.adapter.driver_primario.dtos.filtros;

import com.techchallenge.devnet.core.domain.models.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.core.domain.models.enums.StatusPedidoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PedidoFiltroDto {

  private String id;

  private StatusPedidoEnum statusPedido;

  private String cliente;

  private FormaPagamentoEnum formaPagamento;

  private PagamentoFiltroDto pagamento;
}

