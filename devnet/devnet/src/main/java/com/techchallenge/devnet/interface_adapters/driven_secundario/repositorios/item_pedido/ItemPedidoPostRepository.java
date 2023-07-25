package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.item_pedido;

import com.techchallenge.devnet.frameworks_and_drivers.db.ItemPedidoRepositoryJpa;
import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ItemPedidoDao;
import com.techchallenge.devnet.application_business_rules.ports.saida.item_pedido.IItemPedidoSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ItemPedidoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ItemPedidoPostRepository implements IItemPedidoSalvarRepositoryPort {

  private final IAdapterSaida mapper;

  private final ItemPedidoRepositoryJpa jpa;

  public ItemPedidoPostRepository(IAdapterSaida mapper,
                                  ItemPedidoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Transactional
  @Override
  public ItemPedidoModel salvar(final ItemPedidoModel itemPedidoModel) {

    return Optional.of(itemPedidoModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, ItemPedidoDao.class))
      .map(this.jpa::save)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, ItemPedidoModel.class))
      .orElseThrow();
  }

//  @Override
//  public void flush() {
//    this.jpa.flush();
//  }
}

