package com.techchallenge.devnet.application_business_rules.use_case.pagamento;

import com.techchallenge.devnet.application_business_rules.ports.entrada.pagamento.IPagamentoBuscarQrCodeServicePort;
import com.techchallenge.devnet.application_business_rules.ports.entrada.pagamento.IPagamentoPesquisarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pagamento.IPagamentoConsultarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pagamento.IPagamentoPesquisarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.PagamentoNaoEncontradoException;
import com.techchallenge.devnet.application_business_rules.ports.saida.pagamento.IGatewayQRCodeGeneratorPort;
import com.techchallenge.devnet.enterprise_business_rules.models.PagamentoModel;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.PagamentoFiltro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PagamentoGetService implements IPagamentoPesquisarServicePort, IPagamentoBuscarQrCodeServicePort {

  private final IGatewayQRCodeGeneratorPort qrCodeGenerator;

  private final IPagamentoPesquisarRepositoryPort pagamentoPesquisarRepository;

  private final IPagamentoConsultarRepositoryPort pagamentoConsultarRepository;

  public PagamentoGetService(IGatewayQRCodeGeneratorPort qrCodeGenerator,
                             IPagamentoPesquisarRepositoryPort pagamentoPesquisarRepository,
                             IPagamentoConsultarRepositoryPort pagamentoConsultarRepository) {
    this.qrCodeGenerator = qrCodeGenerator;
    this.pagamentoPesquisarRepository = pagamentoPesquisarRepository;
    this.pagamentoConsultarRepository = pagamentoConsultarRepository;
  }

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

