package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;
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

  @Autowired
  private IClienteRepository.GetRepository clienteRepository;

  @Autowired
  private IProdutoRepository.GetRepository produtoRepository;

  @Transactional
  @Override
  public PedidoDtoResponse cadastrar(final PedidoDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto, Pedido.class))
      .map(pedido -> {
        var idCliente = pedido.getCliente().getId();

        var cliente = this.clienteRepository.consultarPorId(idCliente)
          .orElseThrow(() -> new ClienteNaoEncontradoException(idCliente));

        pedido.setCliente(cliente);
        return pedido;
      })
      .map(pedido -> {
        pedido.getItensPedido().forEach(item -> {
          var idProduto = item.getProduto().getId();

          var produto = this.produtoRepository.consultarPorId(idProduto)
            .orElseThrow(() -> new ProdutoNaoEncontradoException(idProduto));

          item.setProduto(produto);
        });
        return pedido;
      })
      .map(pedido -> {
        pedido.setStatusPedido(StatusPedidoEnum.CRIADO);
        pedido.getItensPedido().forEach(item -> item.setPedido(pedido));
        pedido.calcularPrecoTotal();
        return pedido;
      })
      .map(this.repository::salvar)
      .map(pedido -> this.mapper.converterEntidadeParaDtoResponse(pedido, PedidoDtoResponse.class))
      .orElseThrow();
  }
}

