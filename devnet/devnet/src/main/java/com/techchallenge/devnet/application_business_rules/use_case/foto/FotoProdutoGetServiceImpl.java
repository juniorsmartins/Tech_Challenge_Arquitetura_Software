package com.techchallenge.devnet.application_business_rules.use_case.foto;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.FotoProdutoNaoEncontradoException;
import com.techchallenge.devnet.application_business_rules.ports.entrada.foto.FotoProdutoConsultarPorIdServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.FotoProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FotoProdutoGetServiceImpl implements FotoProdutoConsultarPorIdServicePort {

  private final FotoProdutoConsultarPorIdRepositoryPort fotoProdutoGetRepository;

  public FotoProdutoGetServiceImpl(FotoProdutoConsultarPorIdRepositoryPort fotoProdutoGetRepository) {
    this.fotoProdutoGetRepository = fotoProdutoGetRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public FotoProdutoModel consultarPorId(final Long id) {

    return this.fotoProdutoGetRepository.consultarPorId(id)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.FOTO_PRODUTO_NAO_ENCONTRADO, id));
        throw new FotoProdutoNaoEncontradoException(id);
      });
  }
}

