package com.techchallenge.devnet.core.application.use_case.cliente;

import com.techchallenge.devnet.core.application.ports.entrada.cliente.IClienteApagarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteApagarRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.pedido.IPedidoBuscarPorIdClienteRepositoryPort;
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
  private IClienteConsultarPorIdRepositoryPort clienteConsultarPorIdRepository;

  @Autowired
  private IClienteApagarRepositoryPort clienteApagarRepository;

  @Autowired
  private IPedidoBuscarPorIdClienteRepositoryPort pedidoGetRepository;

  @Override
  public void deletar(final Long id) {

    this.clienteConsultarPorIdRepository.consultarPorId(id)
      .map(this::verificarUsoDoCliente)
      .map(model -> {
        this.clienteApagarRepository.deletar(model);
        return true;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, id));
        throw new ClienteNaoEncontradoException(id);
      });
  }

  private ClienteModel verificarUsoDoCliente(final ClienteModel clienteModel) {

    var idCliente = clienteModel.getId();
    var existePedidoDesseCliente = this.pedidoGetRepository.buscarPorIdDeCliente(idCliente)
      .isEmpty();

    if (!existePedidoDesseCliente) {
      log.info(String.format(MensagemPadrao.BLOQUEADO_POR_USO, idCliente));
      throw new DeletarBloqueadoPoUso(idCliente);
    }

    return clienteModel;
  }
}

