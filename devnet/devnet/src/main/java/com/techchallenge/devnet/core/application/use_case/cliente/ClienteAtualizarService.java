package com.techchallenge.devnet.core.application.use_case.cliente;

import com.techchallenge.devnet.core.application.ports.entrada.cliente.IClienteAtualizarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.base.assertions_concern.RegrasCliente;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClienteAtualizarService implements IClienteAtualizarServicePort {

  @Autowired
  private IClienteConsultarPorIdRepositoryPort clienteConsultarPorIdRepository;

  @Autowired
  private IClienteSalvarRepositoryPort clienteSalvarRepository;

  @Autowired
  private List<RegrasCliente> regras;

  @Override
  public ClienteModel atualizar(final Long id, final ClienteModel clienteModel) {

    return this.clienteConsultarPorIdRepository.consultarPorId(id)
      .map(model -> {
        clienteModel.setId(id);
        this.regras.forEach(regra -> regra.executar(clienteModel));
        BeanUtils.copyProperties(clienteModel, model, "id");
        return model;
      })
      .map(this.clienteSalvarRepository::salvar)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.CLIENTE_NAO_ENCONTRADO, id));
        throw new ClienteNaoEncontradoException(id);
      });
  }
}

