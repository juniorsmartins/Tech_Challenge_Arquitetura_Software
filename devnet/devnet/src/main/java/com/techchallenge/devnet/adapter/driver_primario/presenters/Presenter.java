package com.techchallenge.devnet.adapter.driver_primario.presenters;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class Presenter implements IPostPresenter, IPutPresenter, IGetPresenter, IDeletePresenter {

  @Override
  public ResponseEntity<Object> post(final Long id, final Object dto) {

    return ResponseEntity
      .created(URI.create("/api/v1/clientes/" + id))
      .body(dto);
  }

  @Override
  public ResponseEntity<Object> put(Object objeto) {

    return ResponseEntity
      .ok()
      .body(objeto);
  }

  @Override
  public ResponseEntity<Object> get(Object objeto) {

    return ResponseEntity
      .ok()
      .body(objeto);
  }

  @Override
  public ResponseEntity<Object> delete() {

    return ResponseEntity
      .noContent()
      .build();
  }
}

