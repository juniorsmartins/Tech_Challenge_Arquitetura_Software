package com.techchallenge.devnet.core.application.use_case;

import com.google.zxing.WriterException;
import com.techchallenge.devnet.core.application.ports.entrada.IPagamentoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IPagamentoRepositoryPort;
import com.techchallenge.devnet.core.domain.base.utilitarios.IQRCodeGenerator;
import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.models.enums.StatusPagamentoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class PagamentoPostService implements IPagamentoServicePort.PostService {

  @Autowired
  private IQRCodeGenerator qrCodeGenerator;

  @Autowired
  private IPagamentoRepositoryPort.PostRepository pagamentoPostRepository;

  @Override
  public PedidoModel iniciarCobrancaDePagamento(final PedidoModel pedidoModel) {

    return Optional.of(pedidoModel)
      .map(model -> {
        var imagemQrCodeRetorno = this.criarQRCode(model);
        return model;
      })
      .map(this::cadastrarPagamento)
      .orElseThrow();
  }

  private InputStreamResource criarQRCode(PedidoModel pedidoModel) {

    try {
      return this.qrCodeGenerator.gerarQRCode(pedidoModel);

    } catch (WriterException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private PedidoModel cadastrarPagamento(PedidoModel pedidoModel) {

    var pagamento = PagamentoModel.builder()
      .statusPagamento(StatusPagamentoEnum.ABERTO)
      .pedido(pedidoModel)
      .nomeImagemQRCode(this.qrCodeGenerator.criarNomeDaImagemQrCode(pedidoModel))
      .build();
    this.pagamentoPostRepository.salvar(pagamento);
    pedidoModel.setPagamento(pagamento);

    return pedidoModel;
  }
}

