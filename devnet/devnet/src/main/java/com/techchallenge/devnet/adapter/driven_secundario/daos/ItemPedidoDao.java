package com.techchallenge.devnet.adapter.driven_secundario.daos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Audited
public final class ItemPedidoDao implements Serializable {

  public static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "quantidade")
  private int quantidade;

  @Column(name = "preco_parcial")
  private BigDecimal precoParcial;

  @ManyToOne
  @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
  private PedidoDao pedido;

  @ManyToOne
  @JoinColumn(name = "produto_id", referencedColumnName = "id", nullable = false)
  private ProdutoDao produto;

//  public void calcularPrecoParcial() {
//    var precoUnitario = this.produto.getPreco();
//    var quantidade = this.quantidade;
//
//    this.setPrecoParcial(precoUnitario.multiply(new BigDecimal(quantidade)));
//  }
}

