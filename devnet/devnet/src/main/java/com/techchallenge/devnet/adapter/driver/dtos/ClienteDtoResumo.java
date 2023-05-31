package com.techchallenge.devnet.adapter.driver.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteDtoResumo(

  @CPF
  String cpf
) { }

