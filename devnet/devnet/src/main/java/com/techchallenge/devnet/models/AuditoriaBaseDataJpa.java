package com.techchallenge.devnet.models;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
public abstract class AuditoriaBaseDataJpa implements Serializable {

  @CreatedDate
  @Column(name = "data_hora_cadastro")
  private Instant dataHoraCadastro;

  @LastModifiedDate
  @Column(name = "data_hora_atualizacao")
  private Instant dataHoraAtualizacao;
}

