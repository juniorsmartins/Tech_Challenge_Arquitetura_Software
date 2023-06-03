package com.techchallenge.devnet.core.application.use_case;

import com.fasterxml.jackson.core.type.TypeReference;
import com.techchallenge.devnet.adapter.driver.dtos.request.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.ClienteDtoResponse;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.domain.base.assertions_concern.RegrasNegocioCliente;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientePostService implements IClienteService.CadastrarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IClienteRepository.PostRepository repository;

  @Autowired
  private List<RegrasNegocioCliente> regrasDeNegocio;

  @Transactional
  @Override
  public ClienteDtoResponse cadastrar(final ClienteDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto, Cliente.class))
      .map(cliente -> {
        this.regrasDeNegocio.forEach(regra -> regra.executarRegrasDeNegocio(cliente));

        return cliente;
      })
      .map(this.repository::salvar)
      .map(cliente -> this.mapper.converterEntidadeParaDtoResponse(cliente, ClienteDtoResponse.class))
      .orElseThrow();
  }
}

