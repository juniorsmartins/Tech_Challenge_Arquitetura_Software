package com.techchallenge.devnet.application_business_rules.use_case.pedido;

import com.techchallenge.devnet.application_business_rules.ports.entrada.pagamento.PagamentoCadastrarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.entrada.pedido.PedidoCadastrarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.PedidoSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.base.utilitarios.UtilsCliente;
import com.techchallenge.devnet.enterprise_business_rules.base.utilitarios.UtilsEmail;
import com.techchallenge.devnet.enterprise_business_rules.base.utilitarios.UtilsProduto;
import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoPostServiceImpl implements PedidoCadastrarServicePort {

  private final UtilsCliente utilsCliente;

  private final UtilsProduto utilsProduto;

  private final UtilsEmail utilsPedido;

  private final PedidoSalvarRepositoryPort pedidoPostRepository;

  private final PagamentoCadastrarServicePort pagamentoPostService;

  public PedidoPostServiceImpl(UtilsCliente utilsCliente,
                               UtilsProduto utilsProduto,
                               UtilsEmail utilsPedido,
                               PedidoSalvarRepositoryPort pedidoPostRepository,
                               PagamentoCadastrarServicePort pagamentoPostService) {
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
      .map(this.utilsCliente::checagemDeCliente)
      .map(this.utilsProduto::confirmarItensDoPedido)
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

