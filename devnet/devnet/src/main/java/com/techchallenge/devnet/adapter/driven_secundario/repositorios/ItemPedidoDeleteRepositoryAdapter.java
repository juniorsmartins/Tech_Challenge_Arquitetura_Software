package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.conversores_saida.IMapperSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ItemPedidoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ItemPedidoRepositoryJpa;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.PedidoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IItemPedidoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.ItemPedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ItemPedidoDeleteRepositoryAdapter implements IItemPedidoRepository.DeleteRepository {

  @Autowired
  private IMapperSaida mapper;

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

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletarItensDoPedido(Long idPedido) {

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

