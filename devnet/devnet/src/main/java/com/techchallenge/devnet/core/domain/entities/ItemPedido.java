package com.techchallenge.devnet.core.domain.entities;

import com.techchallenge.devnet.core.domain.entities.chave.ItemPedidoId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;

import java.io.Serializable;

@Entity
@Table(name = "itens")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Audited
@IdClass(ItemPedidoId.class)
public final class ItemPedido implements Serializable {

  public static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
  @Id
  @Column(name = "pedido_id")
  private Long pedidoId;

  @EqualsAndHashCode.Include
  @Id
  @Column(name = "produto_id")
  private Long produtoId;

  @ManyToOne(optional = false)
  @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
  private Pedido pedido;

  @ManyToOne(optional = false)
  @JoinColumn(name = "produto_id", insertable = false, updatable = false)
  private Produto produto;

  @Column(name = "quantidade")
  private int quantidade;
}

