package com.techchallenge.devnet.core.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class FotoProdutoModel implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private ProdutoModel produto;

  private String nome;

  private String descricao;

  private String tipo;

  private long tamanho;
}

