package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoResponse;

public interface IProdutoService {

  interface CadastrarService {
    ProdutoDtoResponse cadastrar(ProdutoDtoRequest dtoRequest);
  }

  interface DeletarService {
    void deletar(Long id);
  }
}

