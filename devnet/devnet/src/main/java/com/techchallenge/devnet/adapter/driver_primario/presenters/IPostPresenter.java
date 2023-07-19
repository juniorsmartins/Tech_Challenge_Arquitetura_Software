package com.techchallenge.devnet.adapter.driver_primario.presenters;

import org.springframework.http.ResponseEntity;

public interface IPostPresenter {

  ResponseEntity<Object> post(Long id, Object object);
}

