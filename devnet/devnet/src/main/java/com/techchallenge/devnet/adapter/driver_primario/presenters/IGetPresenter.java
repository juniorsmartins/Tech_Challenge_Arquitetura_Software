package com.techchallenge.devnet.adapter.driver_primario.presenters;

import org.springframework.http.ResponseEntity;

public interface IGetPresenter {

  ResponseEntity<Object> get(Object objeto);
}

