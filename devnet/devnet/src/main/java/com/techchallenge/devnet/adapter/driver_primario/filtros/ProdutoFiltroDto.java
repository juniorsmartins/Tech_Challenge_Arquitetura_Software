package com.techchallenge.devnet.adapter.driver_primario.filtros;

import com.techchallenge.devnet.core.domain.models.enums.CategoriaEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public final class ProdutoFiltroDto {

  private String id;

  private CategoriaEnum categoria;

  private String nome;

  private String descricao;

  private BigDecimal preco;
}

