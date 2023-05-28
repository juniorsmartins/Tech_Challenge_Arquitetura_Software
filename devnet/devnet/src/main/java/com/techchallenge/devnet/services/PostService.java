package com.techchallenge.devnet.services;

import com.techchallenge.devnet.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.mappers.ClienteMapper;
import com.techchallenge.devnet.models.Cliente;
import com.techchallenge.devnet.repositories.PoliticaRepository;
import com.techchallenge.devnet.services.regras_negocio.RegrasNegocioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements PoliticaService.CadastrarService<ClienteDtoRequest, ClienteDtoResponse> {

  @Autowired
  private ClienteMapper mapper;

  @Autowired
  private PoliticaRepository.ClienteSalvarRepository<Cliente> repository;

  @Autowired
  private List<RegrasNegocioCliente> regrasDeNegocio;

  @Override
  public ClienteDtoResponse cadastrar(final ClienteDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converteDtoRequestParaEntidade(dto))
      .map(entidade -> {
        this.regrasDeNegocio.forEach(regra -> regra.executarRegrasDeNegocio(entidade));

        entidade.setDeletado(false);
        return entidade;
      })
      .map(entidade -> this.repository.salvar(entidade))
      .map(entidade -> this.mapper.converteEntidadeParaDtoResponse(entidade))
      .orElseThrow();
  }
}

