package com.techchallenge.devnet.enterprise_business_rules.models;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.CategoriaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class ProdutoModel implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  @Enumerated(EnumType.STRING)
  private CategoriaEnum categoria;

  private String nome;

  private String descricao;

  private BigDecimal preco;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;
}

