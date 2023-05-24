package com.techchallenge.devnet.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteDtoResponse(

  String codigo,

  String nome,

  String cpf,

  String email
) { }

