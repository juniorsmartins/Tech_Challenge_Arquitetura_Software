package com.techchallenge.devnet.interface_adapters.driver_primario.filtros;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public final class ClienteFiltroDto {

  private String id;

  @Length(max = 100)
  private String nome;

  @Length(max = 14)
  private String cpf;

  @Length(max = 100)
  private String email;
}

