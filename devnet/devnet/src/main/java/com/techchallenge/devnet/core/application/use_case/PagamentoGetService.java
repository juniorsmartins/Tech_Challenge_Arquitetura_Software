package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IPagamentoService;
import com.techchallenge.devnet.core.application.ports.saida.IPagamentoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PagamentoNaoEncontradoException;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.core.domain.base.utilitarios.QRCodeGenerator;
import com.techchallenge.devnet.core.domain.value_objects.filtros.PagamentoFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PagamentoGetService implements IPagamentoService.PesquisarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IPagamentoRepository.GetRepository pagamentoGetRepository;

  @Transactional(readOnly = true)
  @Override
  public Page<PagamentoDtoResponse> pesquisar(final PagamentoFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.pagamentoGetRepository.pesquisar(parametrosDePesquisa, paginacao))
      .map(paginaPagamentos -> this.mapper.converterPaginaDeEntidadeParaPaginaDtoResponse(paginaPagamentos, PagamentoDtoResponse.class))
      .orElseThrow();
  }

  @Transactional(readOnly = true)
  @Override
  public InputStreamResource buscarQrCodePorId(final Long id) {

    return this.pagamentoGetRepository.consultarPorId(id)
      .map(pagamento -> {
        var inputStream = QRCodeGenerator.recuperarImagemQrCode(pagamento.getNomeImagemQRCode());
       return new InputStreamResource(inputStream);
      })
      .orElseThrow(() -> new PagamentoNaoEncontradoException(id));
  }
}

