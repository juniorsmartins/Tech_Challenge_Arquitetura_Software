package com.techchallenge.devnet.adapter.driver.dtos.request;

import com.techchallenge.devnet.core.domain.entities.enums.ECategoria;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public final class ProdutoDtoRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @Enumerated(EnumType.STRING)
  private ECategoria categoria;

  @NotBlank
  @Length(max = 100)
  private String nome;

  @NotBlank
  @Length(max = 250)
  private String descricao;

  @NotNull
  @PositiveOrZero
  private BigDecimal preco;
}

