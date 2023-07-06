package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.core.domain.objects.Indicador;

public interface IAdminService {

  interface GetService {
    Indicador buscarIndicadores();
  }
}

