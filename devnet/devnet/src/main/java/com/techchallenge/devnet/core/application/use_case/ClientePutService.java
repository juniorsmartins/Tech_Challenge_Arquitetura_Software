package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.domain.base.mappers.ClienteMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientePutService implements IClienteService.AtualizarService {

  @Autowired
  private ClienteMapper mapper;

  @Autowired
  private IClienteRepository.GetRepository repository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public ClienteDtoResponse atualizar(final Long id, final ClienteDtoRequest dtoRequest) {

    return this.repository.consultarPorId(id)
      .map(cliente -> {
        BeanUtils.copyProperties(dtoRequest, cliente, "id");
        return cliente;
      })
      .map(cliente -> this.mapper.converteEntidadeParaDtoResponse(cliente))
      .orElseThrow();
  }
}

