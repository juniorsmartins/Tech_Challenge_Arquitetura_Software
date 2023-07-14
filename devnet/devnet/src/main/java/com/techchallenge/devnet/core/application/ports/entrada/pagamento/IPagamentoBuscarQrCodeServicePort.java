package com.techchallenge.devnet.core.application.ports.entrada.pagamento;

import org.springframework.core.io.InputStreamResource;

public interface IPagamentoBuscarQrCodeServicePort {

  InputStreamResource buscarQrCodePorId(Long id);
}

