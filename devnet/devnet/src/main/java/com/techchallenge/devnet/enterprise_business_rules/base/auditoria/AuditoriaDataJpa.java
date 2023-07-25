package com.techchallenge.devnet.enterprise_business_rules.base.auditoria;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.OffsetDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Audited
@Getter
@Setter
public abstract class AuditoriaDataJpa implements Serializable {

  @CreationTimestamp
  @Column(name = "data_hora_cadastro", updatable = false)
  private OffsetDateTime dataHoraCadastro = OffsetDateTime.now();

  @UpdateTimestamp
  @Column(name = "data_hora_atualizacao", updatable = true)
  private OffsetDateTime dataHoraAtualizacao = OffsetDateTime.now();
}

