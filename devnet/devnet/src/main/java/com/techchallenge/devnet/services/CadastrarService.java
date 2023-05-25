package com.techchallenge.devnet.services;

import com.techchallenge.devnet.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.mappers.ClienteMapper;
import com.techchallenge.devnet.repositories.PoliticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastrarService implements PoliticaService.CadastrarService {

  @Autowired
  private ClienteMapper mapper;

  @Autowired
  private PoliticaRepository.ClienteSalvarRepository repository;

  @Override
  public ClienteDtoResponse cadastrar(final ClienteDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converteDtoRequestParaEntidade(dto))
      .map(entidade -> {
        entidade.setDeletado(false);
        return entidade;
      })
      .map(entidade -> this.repository.salvar(entidade))
      .map(entidade -> this.mapper.converteEntidadeParaDtoResponse(entidade))
      .orElseThrow();
  }
}

