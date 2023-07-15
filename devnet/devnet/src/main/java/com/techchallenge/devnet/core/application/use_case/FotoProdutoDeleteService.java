package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IFotoProdutoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IFotoProdutoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.foto.IArmazemFotoProdutoRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.FotoProdutoNaoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FotoProdutoDeleteService implements IFotoProdutoServicePort.DeleteService {

  @Autowired
  private IFotoProdutoRepositoryPort.GetRepository fotoProdutoGetRepository;

  @Autowired
  private IFotoProdutoRepositoryPort.PostRepository fotoProdutoPostRepository;

  @Autowired
  private IFotoProdutoRepositoryPort.DeleteRepository fotoProdutoDeleteRepository;

  @Autowired
  private IArmazemFotoProdutoRepositoryPort armazemFotoProdutoService;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletarPorId(final Long id) {

    this.fotoProdutoGetRepository.consultarPorId(id)
      .map(model -> {

        this.fotoProdutoDeleteRepository.deletar(model);
        this.fotoProdutoPostRepository.flush();
        this.armazemFotoProdutoService.remover(model.getNome());

        return model;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.FOTO_PRODUTO_NAO_ENCONTRADO, id));
        throw new FotoProdutoNaoEncontradoException(id);
      });
  }
}

