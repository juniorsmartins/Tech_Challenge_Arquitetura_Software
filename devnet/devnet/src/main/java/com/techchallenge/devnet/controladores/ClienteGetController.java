package com.techchallenge.devnet.controladores;

import com.techchallenge.devnet.dtos.ClienteDtoResponse;
import com.techchallenge.devnet.filtros.ClienteFiltro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/v1/clientes")
public final class ClienteGetController implements PoliticaController.GetController {

  @Override
  public ResponseEntity<Page<ClienteDtoResponse>> pesquisar(final ClienteFiltro filtro,
    @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) final Pageable paginacao) {

    return null;
  }
}

