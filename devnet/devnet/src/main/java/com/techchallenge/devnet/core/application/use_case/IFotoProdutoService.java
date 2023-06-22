package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.ImagemDto;
import com.techchallenge.devnet.adapter.driver.dtos.requisicao.FotoProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.resposta.FotoProdutoDtoResponse;

import java.io.IOException;

public interface IFotoProdutoService {

  interface AtualizarService {
    FotoProdutoDtoResponse inserirFotoNoProduto(Long id, FotoProdutoDtoRequest fotoProdutoDtoRequest) throws IOException;
  }

  interface PesquisarService {
    FotoProdutoDtoResponse consultarPorId(Long id);

    ImagemDto consultarImagemPorId(Long id, String acceptHeader);
  }

  interface DeletarService {
    void deletarPorId(Long id);
  }
}

