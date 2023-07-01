package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.core.domain.value_objects.Indicador;

public interface IAdminService {

  interface GetService {
    Indicador buscarIndicadores();
  }
}

