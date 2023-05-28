package com.techchallenge.devnet.adapter.driver.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteDtoResponse(

  Long id,

  String nome,

  String cpf,

  String email,

  Instant dataHoraCadastro,

  Instant dataHoraAtualizacao
) implements PoliticaDtoResponse { }

