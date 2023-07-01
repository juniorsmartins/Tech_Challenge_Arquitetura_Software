package com.techchallenge.devnet.core.application.use_case;

import com.google.zxing.WriterException;
import com.techchallenge.devnet.core.application.ports.entrada.IPagamentoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IPagamentoRepositoryPort;
import com.techchallenge.devnet.core.domain.base.utilitarios.QRCodeGenerator;
import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import com.techchallenge.devnet.adapter.driven_secundario.entities.PedidoEntity;
import com.techchallenge.devnet.core.domain.models.enums.StatusPagamentoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class PagamentoPostService implements IPagamentoServicePort.PostService {

  @Autowired
  private IPagamentoRepositoryPort.PostRepository pagamentoPostRepository;

  @Override
  public PedidoEntity iniciarCobrancaDePagamento(final PedidoEntity pedido) {

    return Optional.of(pedido)
      .map(entidade -> {
        var imagemQrCodeRetorno = this.criarQRCode(entidade);
        return entidade;
      })
      .map(this::cadastrarPagamento)
      .orElseThrow();
  }

  private InputStreamResource criarQRCode(PedidoEntity pedido) {

    try {
      return QRCodeGenerator.gerarQRCode(pedido);

    } catch (WriterException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private PedidoEntity cadastrarPagamento(PedidoEntity pedido) {

    var pagamento = PagamentoModel.builder()
      .statusPagamento(StatusPagamentoEnum.ABERTO)
      .pedido(pedido)
      .nomeImagemQRCode(QRCodeGenerator.criarNomeDaImagemQrCode(pedido))
      .build();
    this.pagamentoPostRepository.salvar(pagamento);
    pedido.setPagamento(pagamento);

    return pedido;
  }
}

