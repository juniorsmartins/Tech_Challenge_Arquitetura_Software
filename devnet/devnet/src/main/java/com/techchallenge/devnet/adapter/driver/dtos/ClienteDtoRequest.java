package com.techchallenge.devnet.adapter.driver.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteDtoRequest(

  @NotBlank
  @Length(max = 100)
  String nome,

  @NotBlank
  @CPF
  @Length(max = 14)
  String cpf,

  @NotBlank
  @Length(max = 100)
  String email
) { }

