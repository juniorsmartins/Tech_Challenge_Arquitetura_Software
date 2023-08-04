package com.techchallenge.devnet.interface_adapters.driver_primario.presenters;

import org.springframework.http.ResponseEntity;

public interface PutPresenter {

  ResponseEntity<Object> put(Object objeto);
}

