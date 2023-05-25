package com.techchallenge.devnet.services;

import com.techchallenge.devnet.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.dtos.ClienteDtoResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarService implements PoliticaService.AtualizarService {

  @Override
  public ClienteDtoResponse atualizar(UUID id, ClienteDtoRequest dtoRequest) {

    return null;
  }
}

