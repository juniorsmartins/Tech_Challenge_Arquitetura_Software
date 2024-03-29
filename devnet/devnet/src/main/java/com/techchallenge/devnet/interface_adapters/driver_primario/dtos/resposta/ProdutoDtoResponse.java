package com.techchallenge.devnet.interface_adapters.driver_primario.dtos.resposta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.CategoriaEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ProdutoDtoResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private CategoriaEnum categoria;

  private String nome;

  private String descricao;

  private BigDecimal preco;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;
}

