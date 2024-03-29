package com.techchallenge.devnet.enterprise_business_rules.models;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
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
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class PedidoModel implements Serializable {

  public static final long serialVersionUID = 1L;

  private Long id;

  private UUID codigo = UUID.randomUUID();

  private StatusPedidoEnum statusPedido;

  private FormaPagamentoEnum formaPagamento;

  private BigDecimal precoTotal;

  private ClienteModel cliente;

  private List<ItemPedidoModel> itensPedido = new ArrayList<>();

  private PagamentoModel pagamento;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;

  public void calcularPrecoTotal() {
    this.getItensPedido().forEach(ItemPedidoModel::calcularPrecoParcial);

    var total = this.getItensPedido().stream()
      .map(ItemPedidoModel::getPrecoParcial)
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    this.setPrecoTotal(total);
  }
}

