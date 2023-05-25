package com.techchallenge.devnet.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteDtoResponse(

  String codigo,

  String nome,

  String cpf,

  String email
) { }

