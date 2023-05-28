package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.core.domain.base.mappers.ClienteMapper;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.domain.base.assertions_concern.RegrasNegocioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientePostService implements IClienteService.CadastrarService {

  @Autowired
  private ClienteMapper mapper;

  @Autowired
  private IClienteRepository.PostRepository repository;

  @Autowired
  private List<RegrasNegocioCliente> regrasDeNegocio;

  @Transactional
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

