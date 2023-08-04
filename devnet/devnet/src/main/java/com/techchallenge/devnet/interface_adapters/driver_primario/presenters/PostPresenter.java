package com.techchallenge.devnet.interface_adapters.driver_primario.presenters;

import org.springframework.http.ResponseEntity;

public interface PostPresenter {

  ResponseEntity<Object> post(Long id, Object dto);
}

