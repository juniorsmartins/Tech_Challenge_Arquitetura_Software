package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.adapter.driver.mappers.ClienteMapper;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import com.techchallenge.devnet.core.domain.ports_repositories.PoliticaClienteRepository;
import com.techchallenge.devnet.core.application.use_case.assertions_concern.RegrasNegocioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements PoliticaService.CadastrarService<ClienteDtoRequest, ClienteDtoResponse> {

  @Autowired
  private ClienteMapper mapper;

  @Autowired
  private PoliticaClienteRepository.ClienteSalvarRepository<Cliente> repository;

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

