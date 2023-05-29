package com.techchallenge.devnet.core.domain.entities.enums;

import lombok.Getter;

@Getter
public enum ECategoria {

  LANCHE("Lanche"),
  ACOMPANHAMENTO("Acompanhamento"),
  BEBIDA("Bebida"),
  SOBREMESA("Sobremesa");

  private String categoria;

  ECategoria(String categoria) {
    this.categoria = categoria;
  }
}

