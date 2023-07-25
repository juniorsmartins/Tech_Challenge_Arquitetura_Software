package com.techchallenge.devnet.frameworks_and_drivers.web;

import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.FotoProdutoDtoRequest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface FotoProdutoControllerPort {

  interface PutController {
    @PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Object> inserirFotoNoProduto(Long produtoId, FotoProdutoDtoRequest fotoProdutoDtoRequest);
  }

  interface ConsultarPorIdController {
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> consultarPorId(Long id);
  }

  interface ConsultarImagemPorIdController {
    @GetMapping(path = "/{id}")
    ResponseEntity<InputStreamResource> buscarImagemPorId(Long id, String acceptHeader);
  }

  interface DeleteController {
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletarPorId(Long id);
  }
}

