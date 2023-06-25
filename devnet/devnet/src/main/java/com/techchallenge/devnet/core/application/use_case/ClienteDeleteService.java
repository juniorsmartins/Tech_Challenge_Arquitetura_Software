package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IClienteService;
import com.techchallenge.devnet.core.application.ports.saida.IClienteRepository;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.DeletarBloqueadoPoUso;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ClienteDeleteService implements IClienteService.DeletarService {

  @Autowired
  private IClienteRepository.GetRepository clienteGetRepository;

  @Autowired
  private IClienteRepository.DeleteRepository clienteDeleteRepository;

  @Autowired
  private IPedidoRepository.GetRepository pedidoGetRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final Long id) {

    this.clienteGetRepository.consultarPorId(id)
      .map(this::verificarUsoDoCliente)
      .map(cliente -> {
        this.clienteDeleteRepository.deletar(cliente);
        return true;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, id));
        throw new ClienteNaoEncontradoException(id);
      });
  }

  private Cliente verificarUsoDoCliente(final Cliente cliente) {

    var idCliente = cliente.getId();

    var existePedidoDesseCliente = this.pedidoGetRepository.consultarPorIdDeCliente(idCliente)
      .isEmpty();

    if (!existePedidoDesseCliente) {
      log.info(String.format(MensagemPadrao.BLOQUEADO_POR_USO, idCliente));
      throw new DeletarBloqueadoPoUso(idCliente);
    }

    return cliente;
  }
}

