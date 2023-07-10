package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.cliente.IClienteApagarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IClienteRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.DeletarBloqueadoPoUso;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClienteDeleteService implements IClienteApagarServicePort {

  @Autowired
  private IClienteRepositoryPort.GetRepository clienteGetRepository;

  @Autowired
  private IClienteRepositoryPort.DeleteRepository clienteDeleteRepository;

  @Autowired
  private IPedidoRepositoryPort.GetRepository pedidoGetRepository;

  @Override
  public void deletar(final Long id) {

    this.clienteGetRepository.consultarPorId(id)
      .map(this::verificarUsoDoCliente)
      .map(model -> {
        this.clienteDeleteRepository.deletar(model);
        return true;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, id));
        throw new ClienteNaoEncontradoException(id);
      });
  }

  private ClienteModel verificarUsoDoCliente(final ClienteModel clienteModel) {

    var idCliente = clienteModel.getId();
    var existePedidoDesseCliente = this.pedidoGetRepository.consultarPorIdDeCliente(idCliente)
      .isEmpty();

    if (!existePedidoDesseCliente) {
      log.info(String.format(MensagemPadrao.BLOQUEADO_POR_USO, idCliente));
      throw new DeletarBloqueadoPoUso(idCliente);
    }

    return clienteModel;
  }
}

