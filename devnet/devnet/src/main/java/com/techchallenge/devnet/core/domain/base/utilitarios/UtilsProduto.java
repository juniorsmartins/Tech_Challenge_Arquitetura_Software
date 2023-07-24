package com.techchallenge.devnet.core.domain.base.utilitarios;

import com.techchallenge.devnet.core.application.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.application.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public final class UtilsProduto implements IUtilsProduto {

  private final IProdutoConsultarPorIdRepositoryPort repositorio;

  public UtilsProduto(IProdutoConsultarPorIdRepositoryPort repositorioConsultarProdutoPorId) {
    this.repositorio = repositorioConsultarProdutoPorId;
  }

  @Override
  public PedidoModel confirmarItensDoPedido(PedidoModel pedidoModel) {

    pedidoModel.getItensPedido().forEach(item -> {
      var produto = this.validarProduto(item.getProduto());
      item.setProduto(produto);
    });

    pedidoModel.calcularPrecoTotal();
    return pedidoModel;
  }

  @Override
  public ProdutoModel validarProduto(final ProdutoModel produtoModel) {

    var idProduto = produtoModel.getId();
    return this.repositorio.consultarPorId(idProduto)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PRODUTO_NAO_ENCONTRADO, idProduto));
        throw new ProdutoNaoEncontradoException(idProduto);
      });
  }
}

