package com.techchallenge.devnet.exceptions;

import lombok.Getter;

@Getter
public enum TipoDeErroEnum {

  REGRA_VIOLADA("Regra de Negócio Violada", "/regra-de-negocio-violada");

  private String titulo;

  private String caminho;

  TipoDeErroEnum(String titulo, String caminho) {
    this.titulo = titulo;
    this.caminho = "http://localhost:8080" + caminho;
  }
}

