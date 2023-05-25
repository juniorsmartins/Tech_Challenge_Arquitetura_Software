package com.techchallenge.devnet.services;

import com.techchallenge.devnet.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.dtos.ClienteDtoResponse;
import org.springframework.stereotype.Service;

@Service
public class CadastrarService implements PoliticaService.CadastrarService {

  @Override
  public ClienteDtoResponse cadastrar(ClienteDtoRequest dtoRequest) {

    return null;
  }
}

