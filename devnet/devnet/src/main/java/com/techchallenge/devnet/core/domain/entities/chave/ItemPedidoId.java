package com.techchallenge.devnet.core.domain.entities.chave;

import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.Produto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedidoId implements Serializable {

  private static final long serialVersionUID = 1L;

  @EqualsAndHashCode.Include
  private Long pedidoId;

  @EqualsAndHashCode.Include
  private Long produtoId;
}

