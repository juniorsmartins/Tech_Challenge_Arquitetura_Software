package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AtualizarService implements PoliticaService.AtualizarService {

  @Override
  public ClienteDtoResponse atualizar(UUID id, ClienteDtoRequest dtoRequest) {

    return null;
  }
}

