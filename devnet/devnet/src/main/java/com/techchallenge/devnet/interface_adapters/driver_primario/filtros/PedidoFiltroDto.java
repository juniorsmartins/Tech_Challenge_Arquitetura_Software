package com.techchallenge.devnet.interface_adapters.driver_primario.filtros;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
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

