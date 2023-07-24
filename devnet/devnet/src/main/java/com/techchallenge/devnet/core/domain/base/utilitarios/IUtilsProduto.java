package com.techchallenge.devnet.core.domain.base.utilitarios;

import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;

public interface IUtilsProduto {
  PedidoModel confirmarItensDoPedido(PedidoModel pedidoModel);

  ProdutoModel validarProduto(ProdutoModel produtoModel);
}

