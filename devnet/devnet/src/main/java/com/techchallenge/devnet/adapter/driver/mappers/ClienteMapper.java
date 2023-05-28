package com.techchallenge.devnet.adapter.driver.mappers;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

  Cliente converteDtoRequestParaEntidade(ClienteDtoRequest dtoRequest);

  ClienteDtoResponse converteEntidadeParaDtoResponse(Cliente cliente);
}

