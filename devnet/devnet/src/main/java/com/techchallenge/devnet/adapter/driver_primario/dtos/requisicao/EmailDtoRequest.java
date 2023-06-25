package com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public final class EmailDtoRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotBlank
  private String ownerRef;

  @NotBlank
  @Email
  private String emailFrom;

  @NotBlank
  @Email
  private String emailTo;

  @NotBlank
  private String subject;

  @NotBlank
  private String text;
}

