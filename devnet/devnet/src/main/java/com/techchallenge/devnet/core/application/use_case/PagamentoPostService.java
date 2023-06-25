package com.techchallenge.devnet.core.application.use_case;

import com.google.zxing.WriterException;
import com.techchallenge.devnet.core.application.ports.entrada.IPagamentoService;
import com.techchallenge.devnet.core.application.ports.saida.IPagamentoRepository;
import com.techchallenge.devnet.core.domain.base.utilitarios.QRCodeGenerator;
import com.techchallenge.devnet.core.domain.entities.Pagamento;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPagamentoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class PagamentoPostService implements IPagamentoService.PostService {

  @Autowired
  private IPagamentoRepository.PostRepository pagamentoPostRepository;

  @Override
  public Pedido iniciarCobrancaDePagamento(final Pedido pedido) {

    return Optional.of(pedido)
      .map(entidade -> {
        var imagemQrCodeRetorno = this.criarQRCode(entidade);
        return entidade;
      })
      .map(this::cadastrarPagamento)
      .orElseThrow();
  }

  private InputStreamResource criarQRCode(Pedido pedido) {

    try {
      return QRCodeGenerator.gerarQRCode(pedido);

    } catch (WriterException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Pedido cadastrarPagamento(Pedido pedido) {

    var pagamento = Pagamento.builder()
      .statusPagamento(StatusPagamentoEnum.ABERTO)
      .pedido(pedido)
      .nomeImagemQRCode(QRCodeGenerator.criarNomeDaImagemQrCode(pedido))
      .build();
    this.pagamentoPostRepository.salvar(pagamento);
    pedido.setPagamento(pagamento);

    return pedido;
  }
}

