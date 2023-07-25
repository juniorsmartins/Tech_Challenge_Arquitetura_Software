package com.techchallenge.devnet.application_business_rules.use_case.produto;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.ports.entrada.produto.IProdutoAtualizarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.IProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.IProdutoSalvarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProdutoPutService implements IProdutoAtualizarServicePort {

  private final IProdutoConsultarPorIdRepositoryPort repositorioConsultarProdutoPorId;

  private final IProdutoSalvarRepositoryPort repositorioSalvarProduto;

  public ProdutoPutService(IProdutoConsultarPorIdRepositoryPort repositorioConsultarProdutoPorId,
                           IProdutoSalvarRepositoryPort repositorioSalvarProduto) {
    this.repositorioConsultarProdutoPorId = repositorioConsultarProdutoPorId;
    this.repositorioSalvarProduto = repositorioSalvarProduto;
  }

  @Override
  public ProdutoModel atualizar(final Long id, final ProdutoModel produtoModel) {

    return this.repositorioConsultarProdutoPorId.consultarPorId(id)
      .map(produto -> {
        BeanUtils.copyProperties(produtoModel, produto, "id");
        return produto;
      })
      .map(this.repositorioSalvarProduto::salvar)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PRODUTO_NAO_ENCONTRADO, id));
        throw new ProdutoNaoEncontradoException(id);
      });
  }
}

