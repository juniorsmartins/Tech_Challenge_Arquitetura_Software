package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IClienteMapper;
import com.techchallenge.devnet.core.domain.base.mappers.IPedidoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoPostService implements IPedidoService.CadastrarService {

  @Autowired
  private IPedidoMapper mapper;

  @Autowired
  private IPedidoRepository.PostRepository repository;

  @Transactional
  @Override
  public PedidoDtoResponse cadastrar(final PedidoDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto))
      .map(this.repository::salvar)
      .map(this.mapper::converterEntidadeParaDtoResponse)
      .orElseThrow();
  }
}

