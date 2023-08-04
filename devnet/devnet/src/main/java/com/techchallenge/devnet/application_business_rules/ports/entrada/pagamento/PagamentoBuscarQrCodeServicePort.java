package com.techchallenge.devnet.application_business_rules.ports.entrada.pagamento;

import org.springframework.core.io.InputStreamResource;

public interface PagamentoBuscarQrCodeServicePort {

  InputStreamResource buscarQrCodePorId(Long id);
}

