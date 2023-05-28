package com.techchallenge.devnet.core.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Audited
@Getter
@Setter
public abstract class AuditoriaBaseDataJpa implements Serializable {

  @CreatedDate
  @Column(name = "data_hora_cadastro", updatable = false)
  private Instant dataHoraCadastro = Instant.now();

  @LastModifiedDate
  @Column(name = "data_hora_atualizacao")
  private Instant dataHoraAtualizacao = Instant.now();
}

