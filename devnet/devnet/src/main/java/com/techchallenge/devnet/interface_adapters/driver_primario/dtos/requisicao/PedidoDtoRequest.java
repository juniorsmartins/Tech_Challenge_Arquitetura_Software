package com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao;

import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.ClienteDtoResumo;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.FormaPagamentoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class PedidoDtoRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @Valid
  private ClienteDtoResumo cliente;

  @NotNull
  @Size(min = 1)
  @Valid
  private List<ItemPedidoDtoRequest> itensPedido;

  @NotNull
  @Enumerated(EnumType.STRING)
  private FormaPagamentoEnum formaPagamento;
}

