package com.techchallenge.devnet.enterprise_business_rules.models.enums;

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

