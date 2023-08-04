package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.item_pedido;

import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ItemPedidoDao;
import com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.pedido.PedidoRepositoryJpa;
import com.techchallenge.devnet.application_business_rules.ports.saida.item_pedido.ItemPedidoApagarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.item_pedido.ItemPedidoDeletarItensRepositoryPort;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.enterprise_business_rules.models.ItemPedidoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ItemPedidoDeleteRepositoryImpl implements ItemPedidoApagarRepositoryPort,
  ItemPedidoDeletarItensRepositoryPort {

  private final AdapterSaida mapper;

  private final ItemPedidoRepositoryJpa jpa;

  private final PedidoRepositoryJpa pedidoJpa;

  public ItemPedidoDeleteRepositoryImpl(AdapterSaida mapper,
                                        ItemPedidoRepositoryJpa jpa,
                                        PedidoRepositoryJpa pedidoJpa) {
    this.mapper = mapper;
    this.jpa = jpa;
    this.pedidoJpa = pedidoJpa;
  }

  @Transactional
  @Override
  public void deletar(final ItemPedidoModel itemPedidoModel) {

    Optional.of(itemPedidoModel)
      .map(model -> {
        var itemPedidoEntity = this.mapper.converterOrigemParaDestino(model, ItemPedidoDao.class);
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

