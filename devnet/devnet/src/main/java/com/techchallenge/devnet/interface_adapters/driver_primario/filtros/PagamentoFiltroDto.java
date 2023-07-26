package com.techchallenge.devnet.interface_adapters.driver_primario.filtros;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPagamentoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PagamentoFiltroDto {

  private String id;

  @Enumerated(EnumType.STRING)
  private StatusPagamentoEnum statusPagamento;

  private String nomeImagemQRCode;
}

