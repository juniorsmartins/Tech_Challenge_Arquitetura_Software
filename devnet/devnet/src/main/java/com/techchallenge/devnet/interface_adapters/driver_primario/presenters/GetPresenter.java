package com.techchallenge.devnet.interface_adapters.driver_primario.presenters;

import org.springframework.http.ResponseEntity;

public interface GetPresenter {

  ResponseEntity<Object> get(Object objeto);
}

