package com.techchallenge.devnet.core.domain.entities.chave;

import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"pedidoId", "produtoId"})
@Embeddable
public class ItemPedidoId implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "pedido_id")
  private Long pedidoId;

  @Column(name = "produto_id")
  private Long produtoId;
}

