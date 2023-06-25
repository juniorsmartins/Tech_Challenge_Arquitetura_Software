package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.core.application.ports.entrada.IFotoProdutoService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/fotos")
public final class FotoProdutoDeleteController implements IFotoProdutoController.DeleteController {

  @Autowired
  private IFotoProdutoService.DeletarService fotoProdutoDeletarService;

  @Override
  public ResponseEntity<Object> deletarPorId(
    @Parameter(name = "id", description = "Chave de identificação", example = "22", required = true)
    @PathVariable(name = "id") final Long id) {

    this.fotoProdutoDeletarService.deletarPorId(id);

    return ResponseEntity
      .noContent()
      .build();
  }
}

