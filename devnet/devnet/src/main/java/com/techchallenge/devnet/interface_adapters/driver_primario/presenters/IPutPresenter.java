package com.techchallenge.devnet.interface_adapters.driver_primario.presenters;

import org.springframework.http.ResponseEntity;

public interface IPutPresenter {

  ResponseEntity<Object> put(Object objeto);
}

