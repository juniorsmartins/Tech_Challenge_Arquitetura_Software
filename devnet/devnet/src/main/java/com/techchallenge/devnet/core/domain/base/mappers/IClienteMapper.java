package com.techchallenge.devnet.core.domain.base.mappers;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IClienteMapper {

  Cliente converterDtoRequestParaEntidade(ClienteDtoRequest dtoRequest);

  ClienteDtoResponse converterEntidadeParaDtoResponse(Cliente cliente);

  default Page<ClienteDtoResponse> converterPaginaDeEntidadesParaPaginaDeDtosResponse(Page<Cliente> pageCliente) {
    var clienteDTOs = pageCliente.getContent()
      .stream()
      .map(this::converterEntidadeParaDtoResponse)
      .collect(Collectors.toList());

    return new PageImpl<>(clienteDTOs, pageCliente.getPageable(), pageCliente.getTotalElements());
  }
}

