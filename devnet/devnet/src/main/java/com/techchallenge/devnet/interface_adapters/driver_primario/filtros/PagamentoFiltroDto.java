package com.techchallenge.devnet.interface_adapters.driver_primario.filtros;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPagamentoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PagamentoFiltroDto {

  private String id;

  private StatusPagamentoEnum statusPagamento;

  private String nomeImagemQRCode;
}

