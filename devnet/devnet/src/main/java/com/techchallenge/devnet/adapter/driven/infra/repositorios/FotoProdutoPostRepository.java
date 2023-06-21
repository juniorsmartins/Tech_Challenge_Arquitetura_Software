package com.techchallenge.devnet.adapter.driven.infra.repositorios;

import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.FotoProdutoRepositoryJpa;
import com.techchallenge.devnet.core.application.ports.IFotoProdutoRepository;
import com.techchallenge.devnet.core.application.ports.ILocalFotoProdutoArmazemService;
import com.techchallenge.devnet.core.domain.entities.FotoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.InputStream;

@Repository
public class FotoProdutoPostRepository implements IFotoProdutoRepository.PostRepository {

  @Autowired
  private FotoProdutoRepositoryJpa fotoProdutoRepositoryJpa;

  @Autowired
  private ILocalFotoProdutoArmazemService localFotoProdutoArmazemService;

  @Override
  public FotoProduto salvar(final FotoProduto fotoProdutoPraSalvar, final InputStream dadosArquivo) {

    var produtoId = fotoProdutoPraSalvar.getProduto().getId();

    var fotoExistente = this.fotoProdutoRepositoryJpa.findById(produtoId);
    String nomeFotoExistente = null;

    if (fotoExistente.isPresent()) {
      nomeFotoExistente = fotoExistente.get().getNome();
      this.fotoProdutoRepositoryJpa.delete(fotoExistente.get());
    }

    var fotoProdutoSalvo = this.fotoProdutoRepositoryJpa.save(fotoProdutoPraSalvar);
    this.fotoProdutoRepositoryJpa.flush();

    var novaFoto = ILocalFotoProdutoArmazemService.NovaFoto.builder()
      .nomeArquivo(fotoProdutoSalvo.getNome())
      .inputStream(dadosArquivo)
      .build();

    this.localFotoProdutoArmazemService.substituir(nomeFotoExistente, novaFoto);

    return fotoProdutoSalvo;
  }

  @Override
  public void flush() {
    this.fotoProdutoRepositoryJpa.flush();
  }
}
