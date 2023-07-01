package com.techchallenge.devnet.core.domain.models;

import com.techchallenge.devnet.core.domain.models.enums.StatusEmailEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class EmailModel implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String ownerRef;

  private String emailFrom;

  private String emailTo;

  private String subject;

  private String text;

  private LocalDateTime sendDataEmail;

  @Enumerated(EnumType.STRING)
  private StatusEmailEnum statusEmail;

  private PedidoModel pedido;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;
}

