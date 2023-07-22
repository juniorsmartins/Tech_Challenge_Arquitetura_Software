package com.techchallenge.devnet.core.application.use_case.pagamento;

import com.techchallenge.devnet.core.application.ports.entrada.pagamento.IPagamentoBuscarQrCodeServicePort;
import com.techchallenge.devnet.core.application.ports.entrada.pagamento.IPagamentoPesquisarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.pagamento.IPagamentoConsultarRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.pagamento.IPagamentoPesquisarRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PagamentoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import com.techchallenge.devnet.core.domain.objects.filtros.PagamentoFiltro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PagamentoGetService implements IPagamentoPesquisarServicePort, IPagamentoBuscarQrCodeServicePort {

  @Autowired
  private IQRCodeGenerator qrCodeGenerator;

  @Autowired
  private IPagamentoPesquisarRepositoryPort pagamentoPesquisarRepository;

  @Autowired
  private IPagamentoConsultarRepositoryPort pagamentoConsultarRepository;

  @Override
  public Page<PagamentoModel> pesquisar(final PagamentoFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.pagamentoPesquisarRepository.pesquisar(parametrosDePesquisa, paginacao))
      .orElseThrow();
  }

  @Override
  public InputStreamResource buscarQrCodePorId(final Long id) {

    return this.pagamentoConsultarRepository.consultarPorId(id)
      .map(model -> {
        var inputStream = this.qrCodeGenerator.recuperarImagemQrCode(model.getNomeImagemQRCode());
        return new InputStreamResource(inputStream);
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PAGAMENTO_NAO_ENCONTRADO, id));
        throw new PagamentoNaoEncontradoException(id);
      });
  }
}
