package com.techchallenge.devnet.core.application.use_case.foto;

import com.techchallenge.devnet.core.application.ports.entrada.foto.IFotoProdutoConsultarPorIdServicePort;
import com.techchallenge.devnet.core.application.ports.saida.foto.IArmazemFotoProdutoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.foto.IFotoProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.FotoProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FotoProdutoConsultarPorIdService implements IFotoProdutoConsultarPorIdServicePort {

  @Autowired
  private IFotoProdutoConsultarPorIdRepositoryPort fotoProdutoGetRepository;

  @Autowired
  private IArmazemFotoProdutoRepositoryPort armazemFotoProdutoService;

  @Transactional(readOnly = true)
  @Override
  public FotoProdutoModel consultarPorId(final Long id) {

    return this.fotoProdutoGetRepository.consultarPorId(id)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.FOTO_PRODUTO_NAO_ENCONTRADO, id));
        throw new FotoProdutoNaoEncontradoException(id);
      });
  }
}

