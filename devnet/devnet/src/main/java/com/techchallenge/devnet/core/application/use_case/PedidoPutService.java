package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoPutService implements IPedidoService.AtualizarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IPedidoRepository.GetRepository repository;

  @Autowired
  private IClienteRepository.GetRepository clienteRepository;

  @Autowired
  private IProdutoRepository.GetRepository produtoRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public PedidoDtoResponse atualizar(final Long id, final PedidoDtoRequest dtoRequest) {

    return this.repository.consultarPorId(id)
      .map(pedido -> {

//        var itensPedido = dtoRequest.getItensPedido()
//          .stream()
//          .map(itemDtoRequest -> this.mapper.converterDtoRequestParaEntidade(itemDtoRequest, ItemPedido.class))
//          .collect(Collectors.toList());
//        pedido.setItensPedido(itensPedido);
        pedido.setItensPedido(null);
        BeanUtils.copyProperties(dtoRequest, pedido, "id");
        pedido.getItensPedido().forEach(item -> item.setPedido(pedido));
        pedido.calcularPrecoTotal();

        return pedido;
      })
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
      .map(pedido -> this.mapper.converterEntidadeParaDtoResponse(pedido, PedidoDtoResponse.class))
      .orElseThrow(() -> new PedidoNaoEncontradoException(id));
  }
}

