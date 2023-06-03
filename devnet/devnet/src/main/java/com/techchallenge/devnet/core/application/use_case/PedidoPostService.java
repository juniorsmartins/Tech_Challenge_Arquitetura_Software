package com.techchallenge.devnet.core.application.use_case;

import com.fasterxml.jackson.core.type.TypeReference;
import com.techchallenge.devnet.adapter.driver.dtos.request.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoPostService implements IPedidoService.CadastrarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IPedidoRepository.PostRepository repository;

  @Transactional
  @Override
  public PedidoDtoResponse cadastrar(final PedidoDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto, Pedido.class))
      .map(pedido -> {
        pedido.getItensPedido().forEach(item -> item.setPedido(pedido));
        this.repository.flush();
        return pedido;
      })
      .map(this.repository::salvar)
      .map(pedido -> this.mapper.converterEntidadeParaDtoResponse(pedido, PedidoDtoResponse.class))
      .orElseThrow();
  }
}

