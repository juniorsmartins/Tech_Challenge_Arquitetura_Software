package com.techchallenge.devnet.frameworks_and_drivers.web;

import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.ProdutoDtoRequest;
import com.techchallenge.devnet.interface_adapters.driver_primario.filtros.ProdutoFiltroDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface ProdutoControllerPort {

  interface PostController {
    @PostMapping
    ResponseEntity<Object> cadastrar(ProdutoDtoRequest dtoRequest);
  }

  interface PutController {
    @PutMapping(path = "/{id}")
    ResponseEntity<Object> atualizar(Long id, ProdutoDtoRequest dtoRequest);
  }

  interface GetController {
    @GetMapping
    ResponseEntity<Object> pesquisar(ProdutoFiltroDto filtroDto, Pageable paginacao);
  }

  interface DeleteController {
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletePorId(Long id);
  }
}

