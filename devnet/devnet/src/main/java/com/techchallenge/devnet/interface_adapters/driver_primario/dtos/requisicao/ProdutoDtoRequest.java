package com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.CategoriaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ProdutoDtoRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @Enumerated(EnumType.STRING)
  private CategoriaEnum categoria;

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

