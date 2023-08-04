package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.item_pedido;

import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.AdapterSaida;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ItemPedidoDao;
import com.techchallenge.devnet.application_business_rules.ports.saida.item_pedido.ItemPedidoSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ItemPedidoModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ItemPedidoPostRepositoryImpl implements ItemPedidoSalvarRepositoryPort {

  private final AdapterSaida mapper;

  private final ItemPedidoRepositoryJpa jpa;

  public ItemPedidoPostRepositoryImpl(AdapterSaida mapper,
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

