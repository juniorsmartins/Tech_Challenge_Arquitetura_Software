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
  private IPedidoRepository.GetRepository getRepository;

  @Autowired
  private IPedidoRepository.DeleteRepository deleteRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final Long id) {

    this.getRepository.consultarPorId(id)
      .map(pedido -> {
        this.deleteRepository.deletar(pedido);
        return true;
      })
      .orElseThrow(() -> new PedidoNaoEncontradoException(id));
  }
}
