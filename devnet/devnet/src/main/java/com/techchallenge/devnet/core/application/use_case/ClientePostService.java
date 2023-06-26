package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.ClienteDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IClienteService;
import com.techchallenge.devnet.core.application.ports.saida.IClienteRepository;
import com.techchallenge.devnet.core.domain.base.assertions_concern.RegrasNegocioCliente;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientePostService implements IClienteService.PostService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IClienteRepository.PostRepository clientePostRepository;

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
      .map(this.clientePostRepository::salvar)
      .map(cliente -> this.mapper.converterEntidadeParaDtoResponse(cliente, ClienteDtoResponse.class))
      .orElseThrow();
  }
}

