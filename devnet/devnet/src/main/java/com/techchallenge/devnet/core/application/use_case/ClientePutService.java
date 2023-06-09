package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.ClienteDtoResponse;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientePutService implements IClienteService.AtualizarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IClienteRepository.GetRepository clienteGetRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public ClienteDtoResponse atualizar(final Long id, final ClienteDtoRequest dtoRequest) {

    return this.clienteGetRepository.consultarPorId(id)
      .map(cliente -> {
        BeanUtils.copyProperties(dtoRequest, cliente, "id");
        return cliente;
      })
      .map(cliente -> this.mapper.converterEntidadeParaDtoResponse(cliente, ClienteDtoResponse.class))
      .orElseThrow(() -> new ClienteNaoEncontradoException(id));
  }
}

