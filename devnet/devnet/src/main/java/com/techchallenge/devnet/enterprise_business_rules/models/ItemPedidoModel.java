package com.techchallenge.devnet.enterprise_business_rules.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(of = {"id"})
public final class ItemPedidoModel implements Serializable {

  public static final long serialVersionUID = 1L;

  private Long id;

  private int quantidade;

  private BigDecimal precoParcial;

  private PedidoModel pedido;

  private ProdutoModel produto;

  public void calcularPrecoParcial() {
    var precoUnitario = this.produto.getPreco();
    var quantidade = this.quantidade;

    this.setPrecoParcial(precoUnitario.multiply(new BigDecimal(quantidade)));
  }
}

