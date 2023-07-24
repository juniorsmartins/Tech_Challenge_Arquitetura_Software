package com.techchallenge.devnet.core.application.use_case.foto;

import com.techchallenge.devnet.core.application.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.application.exceptions.http_404.FotoProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.application.ports.entrada.foto.IFotoProdutoConsultarPorIdServicePort;
import com.techchallenge.devnet.core.application.ports.saida.foto.IFotoProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FotoProdutoGetService implements IFotoProdutoConsultarPorIdServicePort {

  private final IFotoProdutoConsultarPorIdRepositoryPort fotoProdutoGetRepository;

  public FotoProdutoGetService(IFotoProdutoConsultarPorIdRepositoryPort fotoProdutoGetRepository) {
    this.fotoProdutoGetRepository = fotoProdutoGetRepository;
  }

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

