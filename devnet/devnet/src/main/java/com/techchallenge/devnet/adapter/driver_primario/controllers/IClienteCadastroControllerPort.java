package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.ClienteDtoResponse;
import com.techchallenge.devnet.adapter.driver_primario.dtos.filtros.ClienteFiltroDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public interface IClienteCadastroControllerPort {

  interface CriarController {
    @PostMapping
    ResponseEntity<Object> cadastrar(ClienteDtoRequest dtoRequest);
  }

  interface AtualizarController {
    @PutMapping(path = "/{id}")
    ResponseEntity<ClienteDtoResponse> atualizar(Long id, ClienteDtoRequest dtoRequest);
  }

  interface PesquisarController {
    @GetMapping
    ResponseEntity<Page<ClienteDtoResponse>> pesquisar(ClienteFiltroDto filtro, Pageable paginacao);
  }

  interface ApagarController {
    @DeleteMapping(path = "/{id}")
    ResponseEntity<Object> deletarPorId(Long id);
  }
}

