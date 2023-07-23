package com.techchallenge.devnet.core.application.use_case.produto;

import com.techchallenge.devnet.core.application.ports.entrada.produto.IProdutoApagarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.item_pedido.IItemPedidoBuscarPorIdProdutoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoApagarRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.application.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.application.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.application.exceptions.http_409.DeletarBloqueadoPoUso;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProdutoDeleteService implements IProdutoApagarServicePort {

  private final IProdutoConsultarPorIdRepositoryPort repositorioConsultarProdutoPorId;

  private final IProdutoApagarRepositoryPort repositorioApagarProduto;

  private final IItemPedidoBuscarPorIdProdutoRepositoryPort repositorioBuscarItemProdutoPorId;

  public ProdutoDeleteService(IProdutoConsultarPorIdRepositoryPort repositorioConsultarProdutoPorId,
                              IProdutoApagarRepositoryPort repositorioApagarProduto,
                              IItemPedidoBuscarPorIdProdutoRepositoryPort repositorioBuscarItemProdutoPorId) {
    this.repositorioConsultarProdutoPorId = repositorioConsultarProdutoPorId;
    this.repositorioApagarProduto = repositorioApagarProduto;
    this.repositorioBuscarItemProdutoPorId = repositorioBuscarItemProdutoPorId;
  }

  @Override
  public void deletar(final Long id) {

    this.repositorioConsultarProdutoPorId.consultarPorId(id)
      .map(this::verificarUsoDoProduto)
      .map(model -> {
        this.repositorioApagarProduto.deletar(model);
        return true;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PRODUTO_NAO_ENCONTRADO, id));
        throw new ProdutoNaoEncontradoException(id);
      });
  }

  private ProdutoModel verificarUsoDoProduto(final ProdutoModel produtoModel) {

    var idProduto = produtoModel.getId();
    var existeItemPedidoComEsseProduto = this.repositorioBuscarItemProdutoPorId.buscarPorIdDeProduto(idProduto)
      .isEmpty();

    if (!existeItemPedidoComEsseProduto) {
      log.info(String.format(MensagemPadrao.BLOQUEADO_POR_USO, idProduto));
      throw new DeletarBloqueadoPoUso(idProduto);
    }

    return produtoModel;
  }
}

