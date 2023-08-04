package com.techchallenge.devnet.enterprise_business_rules.base.utilitarios;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.ProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public final class UtilsProdutoImpl implements UtilsProduto {

  private final ProdutoConsultarPorIdRepositoryPort repositorio;

  public UtilsProdutoImpl(ProdutoConsultarPorIdRepositoryPort repositorioConsultarProdutoPorId) {
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

