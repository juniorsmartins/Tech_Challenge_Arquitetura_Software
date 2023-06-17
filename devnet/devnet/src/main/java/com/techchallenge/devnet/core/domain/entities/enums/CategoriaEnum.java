package com.techchallenge.devnet.core.domain.entities.enums;

import lombok.Getter;

@Getter
public enum CategoriaEnum {

  LANCHE("Lanche"),
  ACOMPANHAMENTO("Acompanhamento"),
  BEBIDA("Bebida"),
  SOBREMESA("Sobremesa");

  private String categoria;

  CategoriaEnum(String categoria) {
    this.categoria = categoria;
  }
}

