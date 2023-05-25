package com.techchallenge.devnet.mappers;

import com.techchallenge.devnet.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.models.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

  Cliente converteDtoRequestParaEntidade(ClienteDtoRequest dtoRequest);

  ClienteDtoResponse converteEntidadeParaDtoResponse(Cliente cliente);
}

