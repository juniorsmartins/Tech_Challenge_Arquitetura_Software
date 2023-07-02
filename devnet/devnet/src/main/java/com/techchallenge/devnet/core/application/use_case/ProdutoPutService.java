package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IProdutoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProdutoPutService implements IProdutoServicePort.PutService {

  @Autowired
  private IProdutoRepositoryPort.GetRepository produtoGetRepository;

  @Autowired
  private IProdutoRepositoryPort.PostRepository produtoPostRepository;

  @Override
  public ProdutoModel atualizar(final Long id, final ProdutoModel produtoModel) {

    return this.produtoGetRepository.consultarPorId(id)
      .map(produto -> {
        BeanUtils.copyProperties(produtoModel, produto, "id");
        return produto;
      })
      .map(this.produtoPostRepository::salvar)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PRODUTO_NAO_ENCONTRADO, id));
        throw new ProdutoNaoEncontradoException(id);
      });
  }
}

