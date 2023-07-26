package com.techchallenge.devnet.interface_adapters.driver_primario.filtros;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.CategoriaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
@Setter
public final class ProdutoFiltroDto {

  private String id;

  @Enumerated(EnumType.STRING)
  private CategoriaEnum categoria;

  @Length(max = 100)
  private String nome;

  @Length(max = 250)
  private String descricao;

  @PositiveOrZero
  private BigDecimal preco;
}

