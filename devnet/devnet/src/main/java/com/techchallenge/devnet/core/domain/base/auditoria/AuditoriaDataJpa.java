package com.techchallenge.devnet.core.domain.base.auditoria;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import java.time.OffsetDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Audited
@Getter
@Setter
public abstract class AuditoriaDataJpa implements Serializable {

  @CreatedDate
  @Column(name = "data_hora_cadastro", updatable = false)
  private OffsetDateTime dataHoraCadastro = OffsetDateTime.now();

  @UpdateTimestamp
  @Column(name = "data_hora_atualizacao")
  private OffsetDateTime dataHoraAtualizacao = OffsetDateTime.now();
}

