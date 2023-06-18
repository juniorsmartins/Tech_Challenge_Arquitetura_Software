package com.techchallenge.devnet.core.domain.value_objects.specification;

import com.techchallenge.devnet.core.domain.entities.enums.CategoriaEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoFiltro {

  private String id;

  private CategoriaEnum categoria;

  private String nome;

  private String descricao;

  private BigDecimal preco;
}

