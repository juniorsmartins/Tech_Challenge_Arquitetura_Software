package com.techchallenge.devnet.core.application.use_case.pagamento;

import com.techchallenge.devnet.core.application.ports.entrada.pagamento.IPagamentoBuscarQrCodeServicePort;
import com.techchallenge.devnet.core.application.ports.saida.pagamento.IPagamentoConsultarRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PagamentoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.utilitarios.IQRCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PagamentoBuscarQrCodeService implements IPagamentoBuscarQrCodeServicePort {

  @Autowired
  private IQRCodeGenerator qrCodeGenerator;

  @Autowired
  private IPagamentoConsultarRepositoryPort pagamentoConsultarRepository;

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

