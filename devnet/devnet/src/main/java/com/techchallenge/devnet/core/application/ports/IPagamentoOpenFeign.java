package com.techchallenge.devnet.core.application.ports;

import com.techchallenge.devnet.adapter.driver.dtos.request.PagamentoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PagamentoDtoResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "pagamento-openfeign", url = "http://localhost:8080")
public interface IPagamentoOpenFeign {

  @PostMapping("/api/v1/pagamentos")
  ResponseEntity<PagamentoDtoResponse> cadastrar(@RequestBody @Valid final PagamentoDtoRequest dtoRequest);
}

