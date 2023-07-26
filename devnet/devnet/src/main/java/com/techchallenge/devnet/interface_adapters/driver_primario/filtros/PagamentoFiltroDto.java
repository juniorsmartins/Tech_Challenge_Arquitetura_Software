package com.techchallenge.devnet.interface_adapters.driver_primario.filtros;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPagamentoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PagamentoFiltroDto {

  @Pattern(regexp = "^[0-9,]*$", message = "Permitido apenas números inteiros e vírgulas. Não são permitidos outros caracteres.")
  private String id;

  @Enumerated(EnumType.STRING)
  private StatusPagamentoEnum statusPagamento;

  private String nomeImagemQRCode;
}

