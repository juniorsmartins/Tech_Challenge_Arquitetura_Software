package com.techchallenge.devnet.core.domain.base.mappers;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.adapter.driver.dtos.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.PedidoDtoResponse;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IPedidoMapper {

  Pedido converterDtoRequestParaEntidade(PedidoDtoRequest dtoRequest);

  PedidoDtoResponse converterEntidadeParaDtoResponse(Pedido pedido);

  default Page<PedidoDtoResponse> converterPaginaDeEntidadesParaPaginaDeDtosResponse(Page<Pedido> pagePedido) {
    var pedidoDTOs = pagePedido.getContent()
      .stream()
      .map(this::converterEntidadeParaDtoResponse)
      .collect(Collectors.toList());

    return new PageImpl<>(pedidoDTOs, pagePedido.getPageable(), pagePedido.getTotalElements());
  }
}

