package com.techchallenge.devnet.controladores;

import com.techchallenge.devnet.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.filtros.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

public interface PoliticaController {

  interface PostController {
    @PostMapping
    ResponseEntity<ClienteDtoResponse> cadastrar(ClienteDtoRequest dtoRequest, UriComponentsBuilder uri);
  }

  interface PutController {
    @PutMapping(path = "/{id}")
    ResponseEntity<ClienteDtoResponse> atualizar(UUID id, ClienteDtoRequest dtoRequest);
  }

  interface GetController {
    @GetMapping
    ResponseEntity<Page<ClienteDtoResponse>> pesquisar(ClienteFiltro filtro, Pageable paginacao);
  }

  interface DeleteController {
    @DeleteMapping(path = "/{id}")
    ResponseEntity<?> deletarPorId(UUID id);
  }
}

