package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ItemPedidoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.item_pedido.ItemPedidoRepositoryJpa;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.pedido.PedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.item_pedido.IItemPedidoApagarRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.item_pedido.IItemPedidoDeletarItensRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ItemPedidoDeleteRepository implements IItemPedidoApagarRepositoryPort,
  IItemPedidoDeletarItensRepositoryPort {

  @Autowired
  private IAdapterSaida mapper;

  @Autowired
  private ItemPedidoRepositoryJpa jpa;

  @Autowired
  private PedidoRepositoryJpa pedidoJpa;

  @Transactional
  @Override
  public void deletar(final ItemPedidoModel itemPedidoModel) {

    Optional.of(itemPedidoModel)
      .map(model -> {
        var itemPedidoEntity = this.mapper.converterOrigemParaDestino(model, ItemPedidoEntity.class);
        this.jpa.delete(itemPedidoEntity);
        return true;
      })
      .orElseThrow();
  }

  @Transactional
  @Override
  public void deletarItensDoPedido(final Long idPedido) {

    var pedidoEntity = this.pedidoJpa.findById(idPedido)
      .map(entity -> {
        entity.getItensPedido().forEach(item -> {
          this.jpa.delete(item);
        });
        entity.setItensPedido(null);
        return entity;
      })
      .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido));
  }
}

