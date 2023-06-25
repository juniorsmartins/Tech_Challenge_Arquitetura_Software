package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.adapter.driver_primario.dtos.ImagemDto;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.FotoProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.FotoProdutoDtoResponse;

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

