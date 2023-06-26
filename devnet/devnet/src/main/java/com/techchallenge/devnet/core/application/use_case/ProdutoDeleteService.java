package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IProdutoService;
import com.techchallenge.devnet.core.application.ports.saida.IItemPedidoRepository;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.DeletarBloqueadoPoUso;
import com.techchallenge.devnet.core.domain.entities.Produto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ProdutoDeleteService implements IProdutoService.DeleteService {

  @Autowired
  private IProdutoRepository.GetRepository produtoGetRepository;

  @Autowired
  private IProdutoRepository.DeleteRepository produtoDeleteRepository;

  @Autowired
  private IItemPedidoRepository.GetRepository itemPedidoGetRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final Long id) {

    this.produtoGetRepository.consultarPorId(id)
      .map(this::verificarUsoDoProduto)
      .map(produto -> {
        this.produtoDeleteRepository.deletar(produto);
        return true;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PRODUTO_NAO_ENCONTRADO, id));
        throw new ProdutoNaoEncontradoException(id);
      });
  }

  private Produto verificarUsoDoProduto(final Produto produto) {

    var idProduto = produto.getId();

    var existeItemPedidoComEsseProduto = this.itemPedidoGetRepository.consultarPorIdDeProduto(idProduto)
      .isEmpty();

    if (!existeItemPedidoComEsseProduto) {
      log.info(String.format(MensagemPadrao.BLOQUEADO_POR_USO, idProduto));
      throw new DeletarBloqueadoPoUso(idProduto);
    }

    return produto;
  }
}

