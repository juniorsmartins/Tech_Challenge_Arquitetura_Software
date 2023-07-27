package com.techchallenge.devnet.interface_adapters.driver_primario.filtros;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public final class ClienteFiltroDto {

//  @Pattern(regexp = "^[0-9,]*$", message = "Permitido apenas números inteiros e vírgulas. Não são permitidos outros caracteres.")
  private String id;

  @Length(max = 100)
  private String nome;

  @Length(max = 14)
  private String cpf;

  @Length(max = 100)
  private String email;
}

