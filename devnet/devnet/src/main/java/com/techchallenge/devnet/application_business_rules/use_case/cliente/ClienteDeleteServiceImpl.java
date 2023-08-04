package com.techchallenge.devnet.application_business_rules.use_case.cliente;

import com.techchallenge.devnet.application_business_rules.exceptions.http_409.DeletarBloqueadoPoUso;
import com.techchallenge.devnet.application_business_rules.ports.entrada.cliente.ClienteApagarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClienteApagarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClienteConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.PedidoBuscarPorIdClienteRepositoryPort;
import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClienteDeleteServiceImpl implements ClienteApagarServicePort {

  private final ClienteConsultarPorIdRepositoryPort repositorioConsultarPorIdCliente;

  private final ClienteApagarRepositoryPort repositorioApagarCliente;

  private final PedidoBuscarPorIdClienteRepositoryPort repositorioBuscarPedidoPorIdCliente;

  public ClienteDeleteServiceImpl(ClienteConsultarPorIdRepositoryPort repositorioConsultarPorIdCliente,
                                  ClienteApagarRepositoryPort repositorioApagarCliente,
                                  PedidoBuscarPorIdClienteRepositoryPort repositorioBuscarPedidoPorIdCliente) {
    this.repositorioConsultarPorIdCliente = repositorioConsultarPorIdCliente;
    this.repositorioApagarCliente = repositorioApagarCliente;
    this.repositorioBuscarPedidoPorIdCliente = repositorioBuscarPedidoPorIdCliente;
  }

  @Override
  public void deletar(final Long id) {

    this.repositorioConsultarPorIdCliente.consultarPorId(id)
      .map(this::verificarUsoDoCliente)
      .map(model -> {
        this.repositorioApagarCliente.deletar(model);
        return true;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, id));
        throw new ClienteNaoEncontradoException(id);
      });
  }

  private ClienteModel verificarUsoDoCliente(final ClienteModel clienteModel) {

    var idCliente = clienteModel.getId();
    var existePedidoDesseCliente = this.repositorioBuscarPedidoPorIdCliente.buscarPorIdDeCliente(idCliente)
      .isEmpty();

    if (!existePedidoDesseCliente) {
      log.info(String.format(MensagemPadrao.BLOQUEADO_POR_USO, idCliente));
      throw new DeletarBloqueadoPoUso(idCliente);
    }

    return clienteModel;
  }
}

