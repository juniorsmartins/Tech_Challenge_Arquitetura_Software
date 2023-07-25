package com.techchallenge.devnet.interface_adapters.driver_primario.controllers.pagamento;

import com.techchallenge.devnet.interface_adapters.driver_primario.filtros.PagamentoFiltroDto;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface PagamentoControllerPort {

  interface PutController {
    @PutMapping(path = "/status/{idPedido}")
    ResponseEntity<Object> verificarStatusNoGateway(Long idPedido);
  }

  interface PesquisarController {
    @GetMapping
    ResponseEntity<Object> pesquisar(PagamentoFiltroDto pagamentoFiltroDto, Pageable paginacao);
  }

  interface BuscarQrCodeController {
    @GetMapping(path = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    ResponseEntity<InputStreamResource> buscarQrCodePorId(Long id);
  }
}

