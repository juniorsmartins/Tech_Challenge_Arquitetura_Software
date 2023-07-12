package com.techchallenge.devnet.core.application.use_case.produto;

import com.techchallenge.devnet.core.application.ports.entrada.produto.IProdutoApagarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IItemPedidoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoApagarRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.DeletarBloqueadoPoUso;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProdutoApagarService implements IProdutoApagarServicePort {

  @Autowired
  private IProdutoConsultarPorIdRepositoryPort produtoConsultarPorIdRepository;

  @Autowired
  private IProdutoApagarRepositoryPort produtoApagarRepository;

  @Autowired
  private IItemPedidoRepositoryPort.GetRepository itemPedidoGetRepository;

  @Override
  public void deletar(final Long id) {

    this.produtoConsultarPorIdRepository.consultarPorId(id)
      .map(this::verificarUsoDoProduto)
      .map(model -> {
        this.produtoApagarRepository.deletar(model);
        return true;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PRODUTO_NAO_ENCONTRADO, id));
        throw new ProdutoNaoEncontradoException(id);
      });
  }

  private ProdutoModel verificarUsoDoProduto(final ProdutoModel produtoModel) {

    var idProduto = produtoModel.getId();
    var existeItemPedidoComEsseProduto = this.itemPedidoGetRepository.consultarPorIdDeProduto(idProduto)
      .isEmpty();

    if (!existeItemPedidoComEsseProduto) {
      log.info(String.format(MensagemPadrao.BLOQUEADO_POR_USO, idProduto));
      throw new DeletarBloqueadoPoUso(idProduto);
    }

    return produtoModel;
  }
}

