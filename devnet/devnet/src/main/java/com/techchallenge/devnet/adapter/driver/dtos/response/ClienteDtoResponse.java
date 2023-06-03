package com.techchallenge.devnet.adapter.driver.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ClienteDtoResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String nome;

  private String cpf;

  private String email;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;
}

