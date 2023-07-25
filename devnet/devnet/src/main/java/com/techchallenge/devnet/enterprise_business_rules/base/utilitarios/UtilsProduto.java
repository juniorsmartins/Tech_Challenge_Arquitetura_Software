package com.techchallenge.devnet.enterprise_business_rules.base.utilitarios;

import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;

public interface UtilsProduto {
  PedidoModel confirmarItensDoPedido(PedidoModel pedidoModel);

  ProdutoModel validarProduto(ProdutoModel produtoModel);
}

