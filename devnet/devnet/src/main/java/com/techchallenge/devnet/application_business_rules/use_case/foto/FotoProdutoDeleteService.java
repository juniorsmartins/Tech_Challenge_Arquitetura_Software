package com.techchallenge.devnet.application_business_rules.use_case.foto;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.FotoProdutoNaoEncontradoException;
import com.techchallenge.devnet.application_business_rules.ports.entrada.foto.IFotoProdutoApagarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.IFotoProdutoApagarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.IFotoProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.IFotoProdutoSalvarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.IFotoProdutoArmazemPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FotoProdutoDeleteService implements IFotoProdutoApagarServicePort {

  private final IFotoProdutoConsultarPorIdRepositoryPort fotoProdutoConsultarPorIdRepository;

  private final IFotoProdutoSalvarRepositoryPort fotoProdutoSalvarRepository;

  private final IFotoProdutoApagarRepositoryPort fotoProdutoApagarRepository;

  private final IFotoProdutoArmazemPort armazemFotoProdutoService;

  public FotoProdutoDeleteService(IFotoProdutoConsultarPorIdRepositoryPort fotoProdutoConsultarPorIdRepository,
                                  IFotoProdutoSalvarRepositoryPort fotoProdutoSalvarRepository,
                                  IFotoProdutoApagarRepositoryPort fotoProdutoApagarRepository,
                                  IFotoProdutoArmazemPort armazemFotoProdutoService) {
    this.fotoProdutoConsultarPorIdRepository = fotoProdutoConsultarPorIdRepository;
    this.fotoProdutoSalvarRepository = fotoProdutoSalvarRepository;
    this.fotoProdutoApagarRepository = fotoProdutoApagarRepository;
    this.armazemFotoProdutoService = armazemFotoProdutoService;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletarPorId(final Long id) {

    this.fotoProdutoConsultarPorIdRepository.consultarPorId(id)
      .map(model -> {

        this.fotoProdutoApagarRepository.deletar(model);
        this.fotoProdutoSalvarRepository.flush();
        this.armazemFotoProdutoService.remover(model.getNome());

        return model;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.FOTO_PRODUTO_NAO_ENCONTRADO, id));
        throw new FotoProdutoNaoEncontradoException(id);
      });
  }
}

