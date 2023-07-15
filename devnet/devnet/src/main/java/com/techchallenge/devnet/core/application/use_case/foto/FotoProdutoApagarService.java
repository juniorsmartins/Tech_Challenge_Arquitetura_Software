package com.techchallenge.devnet.core.application.use_case.foto;

import com.techchallenge.devnet.core.application.ports.entrada.foto.IFotoProdutoApagarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.foto.IArmazemFotoProdutoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.foto.IFotoProdutoApagarRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.foto.IFotoProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.foto.IFotoProdutoSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.FotoProdutoNaoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FotoProdutoApagarService implements IFotoProdutoApagarServicePort {

  @Autowired
  private IFotoProdutoConsultarPorIdRepositoryPort fotoProdutoConsultarPorIdRepository;

  @Autowired
  private IFotoProdutoSalvarRepositoryPort fotoProdutoSalvarRepository;

  @Autowired
  private IFotoProdutoApagarRepositoryPort fotoProdutoApagarRepository;

  @Autowired
  private IArmazemFotoProdutoRepositoryPort armazemFotoProdutoService;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletarPorId(final Long id) {

    this.fotoProdutoConsultarPorIdRepository.consultarPorId(id)
      .map(model -> {

        this.fotoProdutoApagarRepository.deletar(model);
        this.fotoProdutoSalvarRepository.flush();
        this.armazemFotoProdutoService.remover(model.getNome());

        return model;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.FOTO_PRODUTO_NAO_ENCONTRADO, id));
        throw new FotoProdutoNaoEncontradoException(id);
      });
  }
}

