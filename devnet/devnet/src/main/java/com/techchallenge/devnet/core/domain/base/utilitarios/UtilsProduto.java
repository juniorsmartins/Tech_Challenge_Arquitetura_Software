package com.techchallenge.devnet.core.domain.base.utilitarios;

import com.techchallenge.devnet.core.application.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import org.springframework.stereotype.Service;

@Service
public final class UtilsProduto implements IUtilsProduto {

  private final IProdutoConsultarPorIdRepositoryPort repositorio;

  public UtilsProduto(IProdutoConsultarPorIdRepositoryPort repositorioConsultarProdutoPorId) {
    this.repositorio = repositorioConsultarProdutoPorId;
  }

  @Override
  public PedidoModel confirmarProdutos(PedidoModel pedidoModel) {

    pedidoModel.getItensPedido().forEach(item -> {
      var idProduto = item.getProduto().getId();
      var produto = this.repositorio.consultarPorId(idProduto)
        .orElseThrow(() -> new ProdutoNaoEncontradoException(idProduto));
      item.setProduto(produto);
    });

    pedidoModel.calcularPrecoTotal();
    return pedidoModel;
  }
}

