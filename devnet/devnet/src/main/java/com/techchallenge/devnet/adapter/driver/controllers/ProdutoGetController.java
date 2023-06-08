package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.adapter.driver.dtos.response.ProdutoDtoResponse;
import com.techchallenge.devnet.core.application.use_case.IProdutoService;
import com.techchallenge.devnet.core.domain.value_objects.ProdutoFiltro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/produtos")
public final class ProdutoGetController implements IProdutoController.GetController {

  @Autowired
  private IProdutoService.PesquisarService service;

  @Override
  public ResponseEntity<Page<ProdutoDtoResponse>> pesquisar(final ProdutoFiltro filtro,
    @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) final Pageable paginacao) {

    var response = Optional.of(filtro)
      .map(parametrosDePesquisa -> this.service.pesquisar(parametrosDePesquisa, paginacao))
      .orElseThrow();

    return ResponseEntity
      .ok()
      .body(response);
  }
}

