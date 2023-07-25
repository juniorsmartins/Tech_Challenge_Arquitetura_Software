package com.techchallenge.devnet.application_business_rules.use_case.pedido;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.application_business_rules.exceptions.http_409.AtualizarPedidoBloqueadoException;
import com.techchallenge.devnet.application_business_rules.ports.entrada.pedido.IPedidoAtualizarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.item_pedido.IItemPedidoDeletarItensRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.IPedidoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.IPedidoSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.base.utilitarios.UtilsCliente;
import com.techchallenge.devnet.enterprise_business_rules.base.utilitarios.UtilsProduto;
import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PedidoPutService implements IPedidoAtualizarServicePort {

  private final UtilsCliente utilsCliente;

  private final UtilsProduto utilsProduto;

  private final IPedidoConsultarPorIdRepositoryPort pedidoGetRepository;

  private final IPedidoSalvarRepositoryPort pedidoPostRepository;

  private final IItemPedidoDeletarItensRepositoryPort itemPedidoDeleteRepository;

  public PedidoPutService(UtilsCliente utilsCliente,
                          UtilsProduto utilsProduto,
                          IPedidoConsultarPorIdRepositoryPort pedidoGetRepository,
                          IPedidoSalvarRepositoryPort pedidoPostRepository,
                          IItemPedidoDeletarItensRepositoryPort itemPedidoDeleteRepository) {
    this.utilsCliente = utilsCliente;
    this.utilsProduto = utilsProduto;
    this.pedidoGetRepository = pedidoGetRepository;
    this.pedidoPostRepository = pedidoPostRepository;
    this.itemPedidoDeleteRepository = itemPedidoDeleteRepository;
  }

  @Override
  public PedidoModel atualizar(final Long id, final PedidoModel pedidoModel) {

    return Optional.of(pedidoModel)
      .map(this.utilsCliente::checagemDeCliente)
      .map(this.utilsProduto::confirmarItensDoPedido)
      .map(model -> {
        model.setStatusPedido(StatusPedidoEnum.RECEBIDO);
        model.getItensPedido().forEach(item -> item.setPedido(model));
        model.setId(id);

        this.removerItensPedidoDoPedidoPorId(id);

        return model;
      })
      .map(this.pedidoPostRepository::salvar)
      .orElseThrow();
  }

  private PedidoModel verificarPermissaoParaAtualizar(PedidoModel pedidoModel) {
    if (!pedidoModel.getStatusPedido().equals(StatusPedidoEnum.RECEBIDO)) {
      log.info(String.format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_ATUALIZAR, pedidoModel.getId(), pedidoModel.getStatusPedido()));
      throw new AtualizarPedidoBloqueadoException(String
        .format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_ATUALIZAR, pedidoModel.getId(), pedidoModel.getStatusPedido()));
    }
    return pedidoModel;
  }

  private void removerItensPedidoDoPedidoPorId(final Long idPedido) {
    this.pedidoGetRepository.consultarPorId(idPedido)
      .map(this::verificarPermissaoParaAtualizar)
      .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido));
    this.itemPedidoDeleteRepository.deletarItensDoPedido(idPedido);
  }
}

