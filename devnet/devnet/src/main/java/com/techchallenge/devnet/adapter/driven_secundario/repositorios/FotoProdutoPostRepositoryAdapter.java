package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.conversores_saida.IMapperSaida;
import com.techchallenge.devnet.adapter.driven_secundario.entities.FotoProdutoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.FotoProdutoRepositoryJpa;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.saida.IFotoProdutoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.ILocalFotoProdutoArmazemService;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Repository
public class FotoProdutoPostRepositoryAdapter implements IFotoProdutoRepositoryPort.PostRepository {

  @Autowired
  private IMapperSaida mapper;

  @Autowired
  private FotoProdutoRepositoryJpa jpa;

  @Autowired
  private ProdutoRepositoryJpa produtoJpa;

  @Autowired
  private ILocalFotoProdutoArmazemService localFotoProdutoArmazemService;

  @Transactional
  @Override
  public FotoProdutoModel salvar(final FotoProdutoModel fotoProdutoModel, final InputStream dadosArquivo) {

    var produtoId = fotoProdutoModel.getProduto().getId();
    var fotoExistente = this.jpa.findById(produtoId);
    String nomeFotoExistente = null;

    if (fotoExistente.isPresent()) {
      nomeFotoExistente = fotoExistente.get().getNome();
      this.jpa.delete(fotoExistente.get());
    }

    var fotoProdutoModelSalvo = Optional.of(fotoProdutoModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, FotoProdutoEntity.class))
      .map(entity -> {
        var idProduto = entity.getProduto().getId();
        var produto = this.produtoJpa.findById(idProduto)
          .orElseThrow(() -> new ProdutoNaoEncontradoException(idProduto));
        entity.setProduto(produto);
        return entity;
      })
      .map(this.jpa::saveAndFlush)
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, FotoProdutoModel.class))
      .orElseThrow();

    var novaFoto = ILocalFotoProdutoArmazemService.NovaFoto.builder()
      .nomeArquivo(fotoProdutoModelSalvo.getNome())
      .inputStream(dadosArquivo)
      .build();

    this.localFotoProdutoArmazemService.substituir(nomeFotoExistente, novaFoto);

    return fotoProdutoModelSalvo;
  }

  @Override
  public void flush() {
    this.jpa.flush();
  }
}
