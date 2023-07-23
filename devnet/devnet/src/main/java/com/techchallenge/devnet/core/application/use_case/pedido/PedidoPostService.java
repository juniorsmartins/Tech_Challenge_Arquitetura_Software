package com.techchallenge.devnet.core.application.use_case.pedido;

import com.techchallenge.devnet.core.application.ports.entrada.pagamento.IPagamentoCadastrarServicePort;
import com.techchallenge.devnet.core.application.ports.entrada.pedido.IPedidoCadastrarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.pedido.IPedidoSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.base.utilitarios.IUtilsCliente;
import com.techchallenge.devnet.core.domain.base.utilitarios.IUtilsEmail;
import com.techchallenge.devnet.core.domain.base.utilitarios.IUtilsProduto;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.models.enums.StatusPedidoEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoPostService implements IPedidoCadastrarServicePort {

  private final IUtilsCliente utilsCliente;

  private final IUtilsProduto utilsProduto;

  private final IUtilsEmail utilsPedido;

  private final IPedidoSalvarRepositoryPort pedidoPostRepository;

  private final IPagamentoCadastrarServicePort pagamentoPostService;

  public PedidoPostService(IUtilsCliente utilsCliente,
                           IUtilsProduto utilsProduto,
                           IUtilsEmail utilsPedido,
                           IPedidoSalvarRepositoryPort pedidoPostRepository,
                           IPagamentoCadastrarServicePort pagamentoPostService) {
    this.utilsPedido = utilsPedido;
    this.utilsCliente = utilsCliente;
    this.utilsProduto = utilsProduto;
    this.pedidoPostRepository = pedidoPostRepository;
    this.pagamentoPostService = pagamentoPostService;
  }

  @Transactional
  @Override
  public PedidoModel cadastrar(final PedidoModel pedidoModel) {

    return Optional.of(pedidoModel)
      .map(this.utilsCliente::confirmarCliente)
      .map(this.utilsProduto::confirmarProdutos)
      .map(this::organizarPedidoParaRegistrar)
      .map(this.pedidoPostRepository::salvar)
      .map(this.pagamentoPostService::iniciarCobrancaDePagamento)
      .map(this.utilsPedido::notificarPedidoRecebido)
      .orElseThrow();
  }

  private PedidoModel organizarPedidoParaRegistrar(PedidoModel pedidoModel) {

    pedidoModel.setStatusPedido(StatusPedidoEnum.RECEBIDO);
    pedidoModel.getItensPedido().forEach(item -> item.setPedido(pedidoModel));
    pedidoModel.calcularPrecoTotal();

    return pedidoModel;
  }
}

