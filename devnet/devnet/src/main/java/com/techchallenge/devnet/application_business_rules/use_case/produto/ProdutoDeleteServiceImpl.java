package com.techchallenge.devnet.application_business_rules.use_case.produto;

import com.techchallenge.devnet.application_business_rules.exceptions.http_409.DeletarBloqueadoPoUso;
import com.techchallenge.devnet.application_business_rules.ports.entrada.produto.ProdutoApagarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.item_pedido.ItemPedidoBuscarPorIdProdutoRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.ProdutoApagarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.ProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProdutoDeleteServiceImpl implements ProdutoApagarServicePort {

  private final ProdutoConsultarPorIdRepositoryPort repositorioConsultarProdutoPorId;

  private final ProdutoApagarRepositoryPort repositorioApagarProduto;

  private final ItemPedidoBuscarPorIdProdutoRepositoryPort repositorioBuscarItemProdutoPorId;

  public ProdutoDeleteServiceImpl(ProdutoConsultarPorIdRepositoryPort repositorioConsultarProdutoPorId,
                                  ProdutoApagarRepositoryPort repositorioApagarProduto,
                                  ItemPedidoBuscarPorIdProdutoRepositoryPort repositorioBuscarItemProdutoPorId) {
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

