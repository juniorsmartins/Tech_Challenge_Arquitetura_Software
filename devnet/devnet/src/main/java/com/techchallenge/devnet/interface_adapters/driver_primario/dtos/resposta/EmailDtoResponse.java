package com.techchallenge.devnet.interface_adapters.driver_primario.dtos.resposta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.PedidoDtoId;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusEmailEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class EmailDtoResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String ownerRef;

  private String emailFrom;

  private String emailTo;

  private String subject;

  private String text;

  private LocalDateTime sendDataEmail;

  private StatusEmailEnum statusEmail;

  private PedidoDtoId pedido;
}

