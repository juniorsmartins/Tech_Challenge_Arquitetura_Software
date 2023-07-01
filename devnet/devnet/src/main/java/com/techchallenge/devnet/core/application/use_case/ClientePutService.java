package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IClienteServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IClienteRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ClientePutService implements IClienteServicePort.PutService {

  @Autowired
  private IClienteRepositoryPort.GetRepository clienteGetRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public ClienteModel atualizar(final Long id, final ClienteModel clienteModel) {

    return this.clienteGetRepository.consultarPorId(id)
      .map(cliente -> {
        BeanUtils.copyProperties(clienteModel, cliente, "id");
        return cliente;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.CLIENTE_NAO_ENCONTRADO, id));
        throw new ClienteNaoEncontradoException(id);
      });
  }
}

