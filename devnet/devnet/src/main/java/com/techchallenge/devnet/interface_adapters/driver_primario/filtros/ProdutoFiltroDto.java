package com.techchallenge.devnet.interface_adapters.driver_primario.filtros;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.CategoriaEnum;
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

