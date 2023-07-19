package com.techchallenge.devnet.adapter.driver_primario.presenters;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class Presenter implements IPostPresenter {

  @Override
  public ResponseEntity<Object> post(final Long id, final Object object) {

    return ResponseEntity
      .created(URI.create("/api/v1/clientes/" + id))
      .body(object);
  }
}

