package com.techchallenge.devnet.adapter.driver_primario.presenters;

import org.springframework.http.ResponseEntity;

public interface IPutPresenter {

  ResponseEntity<Object> put(Object objeto);
}

