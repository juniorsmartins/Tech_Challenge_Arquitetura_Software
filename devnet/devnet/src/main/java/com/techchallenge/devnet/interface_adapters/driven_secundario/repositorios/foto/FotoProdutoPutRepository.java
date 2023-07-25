package com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.foto;

import com.techchallenge.devnet.frameworks_and_drivers.db.FotoProdutoRepositoryJpa;
import com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida.IAdapterSaida;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.FotoProdutoDao;
import com.techchallenge.devnet.frameworks_and_drivers.db.ProdutoRepositoryJpa;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.IFotoProdutoArmazemPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.IFotoProdutoSalvarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Repository
public class FotoProdutoPutRepository implements IFotoProdutoSalvarRepositoryPort {

  private final IAdapterSaida mapper;

  private final FotoProdutoRepositoryJpa jpa;

  public FotoProdutoPutRepository(IAdapterSaida mapper,
                                  FotoProdutoRepositoryJpa jpa) {
    this.mapper = mapper;
    this.jpa = jpa;
  }

  @Autowired
  private ProdutoRepositoryJpa produtoJpa;

  @Autowired
  private IFotoProdutoArmazemPort armazemFotoProdutoService;

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
      .map(model -> this.mapper.converterOrigemParaDestino(model, FotoProdutoDao.class))
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

    var novaFoto = IFotoProdutoArmazemPort.NovaFoto.builder()
      .nomeArquivo(fotoProdutoModelSalvo.getNome())
      .inputStream(dadosArquivo)
      .build();

    this.armazemFotoProdutoService.substituir(nomeFotoExistente, novaFoto);

    return fotoProdutoModelSalvo;
  }

  @Override
  public void flush() {
    this.jpa.flush();
  }
}

