package com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao;

import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.PedidoDtoId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
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

  @NotNull
  @Valid
  private PedidoDtoId pedido;
}

