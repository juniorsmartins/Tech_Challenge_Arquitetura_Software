package com.techchallenge.devnet.enterprise_business_rules.objects.filtros;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.CategoriaEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public final class ProdutoFiltro {

  private String id;

  private CategoriaEnum categoria;

  private String nome;

  private String descricao;

  private BigDecimal preco;
}

