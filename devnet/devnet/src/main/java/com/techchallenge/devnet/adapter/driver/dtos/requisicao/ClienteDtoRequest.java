package com.techchallenge.devnet.adapter.driver.dtos.requisicao;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ClienteDtoRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotBlank
  @Length(max = 100)
  private String nome;

  @NotBlank
  @CPF
  @Length(max = 14)
  private String cpf;

  @NotBlank
  @Length(max = 100)
  private String email;
}

