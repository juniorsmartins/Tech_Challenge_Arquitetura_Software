package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.IndicadoresDtoResponse;

public interface IAdminService {

  interface GetService {
    IndicadoresDtoResponse buscarIndicadores();
  }
}

