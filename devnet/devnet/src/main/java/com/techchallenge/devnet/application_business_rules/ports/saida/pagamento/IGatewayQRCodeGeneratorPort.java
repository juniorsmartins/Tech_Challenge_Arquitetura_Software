package com.techchallenge.devnet.application_business_rules.ports.saida.pagamento;

import com.google.zxing.WriterException;
import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.io.InputStream;

public interface IGatewayQRCodeGeneratorPort {

  InputStreamResource gerarQRCode(PedidoModel pedidoModel) throws WriterException, IOException;

  String criarNomeDaImagemQrCode(PedidoModel pedidoModel);

  InputStream recuperarImagemQrCode(String nomeDaImagemQrCode);
}

