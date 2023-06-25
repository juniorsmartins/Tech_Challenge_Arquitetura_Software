package com.techchallenge.devnet.adapter.driver_primario.dtos;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class ClienteDtoResumo implements Serializable {

  private static final long serialVersionUID = 1L;

  @Positive
  private Long id;

  @CPF
  private String cpf;
}

