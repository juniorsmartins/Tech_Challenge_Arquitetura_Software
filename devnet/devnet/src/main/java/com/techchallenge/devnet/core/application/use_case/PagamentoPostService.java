package com.techchallenge.devnet.core.application.use_case;

import com.google.zxing.WriterException;
import com.techchallenge.devnet.adapter.driver.dtos.response.PagamentoDtoResponse;
import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IPagamentoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.base.utilitarios.QRCodeGenerator;
import com.techchallenge.devnet.core.domain.entities.Pagamento;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.core.domain.value_objects.CobrancaPagamentoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class PagamentoPostService implements IPagamentoService.PagamentoPostService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IPagamentoRepository.PostRepository pagamentoPostRepository;

//  @Autowired
//  private QRCodeGenerator qrCodeGenerator;

  @Override
  public CobrancaPagamentoDto iniciarCobrancaDePagamento(final Pedido pedido) {

    return Optional.of(pedido)
      .map(entidade -> {

        this.criarQRCode(entidade);
        var pagamento = this.cadastrarPagamento(entidade);

        var pagamentoDtoResponse = PagamentoDtoResponse.builder()
          .id(pagamento.getId())
//          .qrCode(qrcode)
          .build();

        var pedidoDtoResponse = this.mapper.converterEntidadeParaDtoResponse(entidade, PedidoDtoResponse.class);

        var cobrancaPagamentoDto = CobrancaPagamentoDto.builder()
          .pedido(pedidoDtoResponse)
          .pagamento(pagamentoDtoResponse)
          .build();

        return cobrancaPagamentoDto;
      })
      .orElseThrow();
  }

  private void criarQRCode(Pedido pedido) {

    try {
      QRCodeGenerator.gerarQRCode(pedido);
    } catch (WriterException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  private Pagamento cadastrarPagamento(Pedido pedido) {

    var pagamento = Pagamento.builder()
      .statusPagamento(StatusPagamentoEnum.ABERTO)
      .pedido(pedido)
      .nomeImagemQRCode(pedido.getId() + QRCodeGenerator.sufixoDoNomeDaImagemDoQRCode)
      .build();
    return this.pagamentoPostRepository.salvar(pagamento);
  }
}

