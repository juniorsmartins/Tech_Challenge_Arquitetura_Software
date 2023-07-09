package com.techchallenge.devnet.core.domain.base.utilitarios;

import com.google.zxing.WriterException;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.io.InputStream;

public interface IQRCodeGenerator {

  InputStreamResource gerarQRCode(PedidoModel pedidoModel) throws WriterException, IOException;

  String criarNomeDaImagemQrCode(PedidoModel pedidoModel);

  InputStream recuperarImagemQrCode(String nomeDaImagemQrCode);
}

