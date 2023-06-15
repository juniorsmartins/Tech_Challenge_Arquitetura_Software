package com.techchallenge.devnet.core.domain.value_objects;

import com.techchallenge.devnet.core.domain.entities.enums.ECategoria;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoFiltro {

  private String id;

  private ECategoria categoria;

  private String nome;

  private String descricao;

  private BigDecimal preco;
}

