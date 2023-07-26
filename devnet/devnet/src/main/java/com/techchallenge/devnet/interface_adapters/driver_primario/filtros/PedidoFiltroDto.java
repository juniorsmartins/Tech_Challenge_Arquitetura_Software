package com.techchallenge.devnet.interface_adapters.driver_primario.filtros;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PedidoFiltroDto {

  @Pattern(regexp = "^[0-9,]*$", message = "Permitido apenas números inteiros e vírgulas. Não são permitidos outros caracteres.")
  private String id;

  @Enumerated(EnumType.STRING)
  private StatusPedidoEnum statusPedido;

  private String cliente;

  @Enumerated(EnumType.STRING)
  private FormaPagamentoEnum formaPagamento;

  private PagamentoFiltroDto pagamento;
}

