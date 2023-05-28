package com.techchallenge.devnet.core.domain.value_objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ClienteFiltro {

  private Long id;

  private String nome;

  private String cpf;

  private String email;
}

