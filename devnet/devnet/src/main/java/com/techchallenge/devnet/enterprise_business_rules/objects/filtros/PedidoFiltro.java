package com.techchallenge.devnet.enterprise_business_rules.objects.filtros;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PedidoFiltro {

  private String id;

  private StatusPedidoEnum statusPedido;

  private String cliente;

  private FormaPagamentoEnum formaPagamento;

  private PagamentoFiltro pagamento;
}

