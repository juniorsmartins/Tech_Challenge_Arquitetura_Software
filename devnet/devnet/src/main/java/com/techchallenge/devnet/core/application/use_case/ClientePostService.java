package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.core.domain.base.mappers.IClienteMapper;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.domain.base.assertions_concern.RegrasNegocioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientePostService implements IClienteService.CadastrarService {

  @Autowired
  private IClienteMapper mapper;

  @Autowired
  private IClienteRepository.PostRepository repository;

  @Autowired
  private List<RegrasNegocioCliente> regrasDeNegocio;

  @Transactional
  @Override
  public ClienteDtoResponse cadastrar(final ClienteDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto))
      .map(cliente -> {
        this.regrasDeNegocio.forEach(regra -> regra.executarRegrasDeNegocio(cliente));

        return cliente;
      })
      .map(cliente -> this.repository.salvar(cliente))
      .map(cliente -> this.mapper.converterEntidadeParaDtoResponse(cliente))
      .orElseThrow();
  }
}

