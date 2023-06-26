package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IFotoProdutoService;
import com.techchallenge.devnet.core.application.ports.saida.IFotoProdutoRepository;
import com.techchallenge.devnet.core.application.ports.saida.ILocalFotoProdutoArmazemService;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.FotoProdutoNaoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FotoProdutoDeleteService implements IFotoProdutoService.DeleteService {

  @Autowired
  private IFotoProdutoRepository.GetRepository fotoProdutoGetRepository;

  @Autowired
  private IFotoProdutoRepository.PostRepository fotoProdutoPostRepository;

  @Autowired
  private IFotoProdutoRepository.DeleteRepository fotoProdutoDeleteRepository;

  @Autowired
  private ILocalFotoProdutoArmazemService localFotoProdutoArmazemService;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletarPorId(final Long id) {

    this.fotoProdutoGetRepository.consultarPorId(id)
      .map(fotoProduto -> {

        this.fotoProdutoDeleteRepository.deletar(fotoProduto);
        this.fotoProdutoPostRepository.flush();
        this.localFotoProdutoArmazemService.remover(fotoProduto.getNome());

        return fotoProduto;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.FOTO_PRODUTO_NAO_ENCONTRADO, id));
        throw new FotoProdutoNaoEncontradoException(id);
      });
  }
}

