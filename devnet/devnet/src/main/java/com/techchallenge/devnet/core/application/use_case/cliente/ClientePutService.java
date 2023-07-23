package com.techchallenge.devnet.core.application.use_case.cliente;

import com.techchallenge.devnet.core.application.ports.entrada.cliente.IClienteAtualizarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.base.assertions_concern.RegrasCliente;
import com.techchallenge.devnet.core.application.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.application.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientePutService implements IClienteAtualizarServicePort {

  private final IClienteConsultarPorIdRepositoryPort repositorioConsultarPorIdCliente;

  private final IClienteSalvarRepositoryPort repositorioSalvarCliente;

  private final List<RegrasCliente> regras;

  public ClientePutService(IClienteConsultarPorIdRepositoryPort repositorioConsultarPorIdCliente,
                           IClienteSalvarRepositoryPort repositorioSalvarCliente,
                           List<RegrasCliente> regras) {
    this.repositorioConsultarPorIdCliente = repositorioConsultarPorIdCliente;
    this.repositorioSalvarCliente = repositorioSalvarCliente;
    this.regras = regras;
  }

  @Override
  public ClienteModel atualizar(final Long id, final ClienteModel clienteModel) {

    return this.repositorioConsultarPorIdCliente.consultarPorId(id)
      .map(model -> {
        clienteModel.setId(id);
        this.regras.forEach(regra -> regra.executar(clienteModel));
        return clienteModel;
      })
      .map(this.repositorioSalvarCliente::salvar)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.CLIENTE_NAO_ENCONTRADO, id));
        throw new ClienteNaoEncontradoException(id);
      });
  }
}

