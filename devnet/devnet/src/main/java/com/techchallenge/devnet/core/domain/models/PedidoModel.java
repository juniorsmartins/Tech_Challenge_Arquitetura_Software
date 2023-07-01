package com.techchallenge.devnet.core.domain.models;

import com.techchallenge.devnet.core.domain.models.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.core.domain.models.enums.StatusPedidoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class PedidoModel implements Serializable {

  public static final long serialVersionUID = 1L;

  private Long id;

  private StatusPedidoEnum statusPedido;

  private FormaPagamentoEnum formaPagamento;

  private BigDecimal precoTotal;

  private ClienteModel cliente;

  private List<ItemPedido> itensPedido = new ArrayList<>();

  private PagamentoModel pagamento;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;

  public void calcularPrecoTotal() {
    this.getItensPedido().forEach(ItemPedido::calcularPrecoParcial);

    var total = this.getItensPedido().stream()
      .map(ItemPedido::getPrecoParcial)
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    this.setPrecoTotal(total);
  }
}

