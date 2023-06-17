package com.techchallenge.devnet.core.application.ports;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "pagamento-openfeign", url = "http://localhost:8080")
public interface IPagamentoOpenFeign {

//  @PostMapping("/api/v1/pagamentos")
//  SolicitacaoDePagamentoDto solicitarPagamento(@RequestBody @Valid final Pedido pedido);
}

