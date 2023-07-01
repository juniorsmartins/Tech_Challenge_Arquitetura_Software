package com.techchallenge.devnet.adapter.driven_secundario.repositorios;

import com.techchallenge.devnet.adapter.driven_secundario.entities.FotoProdutoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.FotoProdutoRepositoryJpa;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.core.application.ports.saida.IFotoProdutoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.ILocalFotoProdutoArmazemService;
import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.Optional;

@Repository
public class FotoProdutoPostRepositoryAdapter implements IFotoProdutoRepositoryPort.PostRepository {

  @Autowired
  private IMapper mapper;

  @Autowired
  private FotoProdutoRepositoryJpa jpa;

  @Autowired
  private ILocalFotoProdutoArmazemService localFotoProdutoArmazemService;

  @Override
  public FotoProdutoModel salvar(final FotoProdutoModel fotoProdutoModel, final InputStream dadosArquivo) {

    var produtoId = fotoProdutoModel.getProduto().getId();

    var fotoExistente = this.jpa.findById(produtoId);
    String nomeFotoExistente = null;

    if (fotoExistente.isPresent()) {
      nomeFotoExistente = fotoExistente.get().getNome();
      this.jpa.delete(fotoExistente.get());
    }

    var fotoProdutoSalvo = Optional.of(fotoProdutoModel)
      .map(model -> this.mapper.converterOrigemParaDestino(model, FotoProdutoEntity.class))
      .map(this.jpa::save)
      .map(entity -> {
        this.jpa.flush();
        return entity;
      })
      .map(entity -> this.mapper.converterOrigemParaDestino(entity, FotoProdutoModel.class))
      .orElseThrow();

    var novaFoto = ILocalFotoProdutoArmazemService.NovaFoto.builder()
      .nomeArquivo(fotoProdutoSalvo.getNome())
      .inputStream(dadosArquivo)
      .build();

    this.localFotoProdutoArmazemService.substituir(nomeFotoExistente, novaFoto);

    return fotoProdutoSalvo;
  }

  @Override
  public void flush() {
    this.jpa.flush();
  }
}
