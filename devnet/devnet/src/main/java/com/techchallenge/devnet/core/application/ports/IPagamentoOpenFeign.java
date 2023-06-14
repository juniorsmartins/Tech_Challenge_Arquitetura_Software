package com.techchallenge.devnet.core.application.ports;

import com.techchallenge.devnet.adapter.driver.dtos.request.PagamentoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PagamentoDtoResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

@FeignClient("pagamento-openfeign")
public interface IPagamentoOpenFeign {

  @RequestMapping(method = RequestMethod.POST, value = "/api/v1/pagamentos")
  ResponseEntity<PagamentoDtoResponse> cadastrar(@RequestBody @Valid final PagamentoDtoRequest dtoRequest, final UriComponentsBuilder uriComponentsBuilder);
}

