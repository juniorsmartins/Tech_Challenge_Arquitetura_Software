package com.techchallenge.devnet.core.domain.entities;

import com.techchallenge.devnet.core.domain.entities.chave.ItemPedidoId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
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
@EqualsAndHashCode(of = {"pedido", "produto"})
@Audited
public final class ItemPedido implements Serializable {

  public static final long serialVersionUID = 1L;

  @EmbeddedId
  private ItemPedidoId id;

  @ManyToOne
  @MapsId("pedidoId")
  @JoinColumn(name = "pedido_id")
  private Pedido pedido;

  @ManyToOne
  @MapsId("produtoId")
  @JoinColumn(name = "produto_id")
  private Produto produto;

  @Column(name = "quantidade")
  private int quantidade;
}

