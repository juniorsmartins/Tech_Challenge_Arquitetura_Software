package com.techchallenge.devnet.core.domain.base.exceptions;

import lombok.Getter;

@Getter
public enum TipoDeErroEnum {

  REGRA_NEGOCIO_VIOLADA("Regra de Negócio Violada.", "/regra-de-negocio-violada"),
  MIDIA_NAO_SUPORTADA("Tipo de mídia não suportada.", "/midia-nao-suportada");

  private String titulo;

  private String caminho;

  TipoDeErroEnum(String titulo, String caminho) {
    this.titulo = titulo;
    this.caminho = "http://localhost:8080" + caminho;
  }
}

