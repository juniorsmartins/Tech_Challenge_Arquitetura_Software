package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoDeleteService implements IPedidoService.DeletarService {

  @Autowired
  private IPedidoRepository.GetRepository pedidoGetRepository;

  @Autowired
  private IPedidoRepository.DeleteRepository pedidoDeleteRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final Long id) {

    this.pedidoGetRepository.consultarPorId(id)
      .map(pedido -> {
        this.pedidoDeleteRepository.deletar(pedido);
        return true;
      })
      .orElseThrow(() -> new PedidoNaoEncontradoException(id));
  }
}
