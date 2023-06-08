package com.techchallenge.devnet.core.domain.base.utils;

import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class UtilsImpl implements IUtils {

  @Autowired
  private IClienteRepository.GetRepository clienteRepository;

  @Autowired
  private IProdutoRepository.GetRepository produtoRepository;

  @Override
  public Pedido confirmarCliente(Pedido pedido) {

    var idCliente = pedido.getCliente().getId();
    var cliente = this.clienteRepository.consultarPorId(idCliente)
      .orElseThrow(() -> new ClienteNaoEncontradoException(idCliente));
    pedido.setCliente(cliente);

    return pedido;
  }

  @Override
  public Pedido confirmarProdutos(Pedido pedido) {

    pedido.getItensPedido().forEach(item -> {
      var idProduto = item.getProduto().getId();
      var produto = this.produtoRepository.consultarPorId(idProduto)
        .orElseThrow(() -> new ProdutoNaoEncontradoException(idProduto));
      item.setProduto(produto);
    });
    pedido.calcularPrecoTotal();
    return pedido;
  }
}

