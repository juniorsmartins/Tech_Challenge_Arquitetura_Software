package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.cliente.IClienteAtualizarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IClienteRepositoryPort;
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
  private IClienteRepositoryPort.GetRepository clienteGetRepository;

  @Autowired
  private IClienteRepositoryPort.PostRepository clientePostRepository;

  @Autowired
  private List<RegrasCliente> regras;

  @Override
  public ClienteModel atualizar(final Long id, final ClienteModel clienteModel) {

    return this.clienteGetRepository.consultarPorId(id)
      .map(model -> {
        clienteModel.setId(id);
        this.regras.forEach(regra -> regra.executar(clienteModel));
        BeanUtils.copyProperties(clienteModel, model, "id");
        return model;
      })
      .map(this.clientePostRepository::salvar)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.CLIENTE_NAO_ENCONTRADO, id));
        throw new ClienteNaoEncontradoException(id);
      });
  }
}

