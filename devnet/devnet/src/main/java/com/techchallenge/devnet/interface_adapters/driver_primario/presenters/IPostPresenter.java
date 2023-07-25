package com.techchallenge.devnet.interface_adapters.driver_primario.presenters;

import org.springframework.http.ResponseEntity;

public interface IPostPresenter {

  ResponseEntity<Object> post(Long id, Object dto);
}

