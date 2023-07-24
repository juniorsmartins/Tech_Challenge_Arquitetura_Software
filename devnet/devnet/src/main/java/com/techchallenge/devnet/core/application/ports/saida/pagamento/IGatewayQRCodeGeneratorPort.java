package com.techchallenge.devnet.core.application.ports.saida.pagamento;

import com.google.zxing.WriterException;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.io.InputStream;

public interface IGatewayQRCodeGeneratorPort {

  InputStreamResource gerarQRCode(PedidoModel pedidoModel) throws WriterException, IOException;

  String criarNomeDaImagemQrCode(PedidoModel pedidoModel);

  InputStream recuperarImagemQrCode(String nomeDaImagemQrCode);
}

