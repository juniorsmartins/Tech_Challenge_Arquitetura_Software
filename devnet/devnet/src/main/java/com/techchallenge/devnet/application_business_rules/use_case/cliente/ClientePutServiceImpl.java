package com.techchallenge.devnet.application_business_rules.use_case.cliente;

import com.techchallenge.devnet.application_business_rules.ports.entrada.cliente.ClienteAtualizarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClienteConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClienteSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.base.assertions_concern.RegrasCliente;
import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientePutServiceImpl implements ClienteAtualizarServicePort {

  private final ClienteConsultarPorIdRepositoryPort repositorioConsultarPorIdCliente;

  private final ClienteSalvarRepositoryPort repositorioSalvarCliente;

  private final List<RegrasCliente> regras;

  public ClientePutServiceImpl(ClienteConsultarPorIdRepositoryPort repositorioConsultarPorIdCliente,
                               ClienteSalvarRepositoryPort repositorioSalvarCliente,
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

