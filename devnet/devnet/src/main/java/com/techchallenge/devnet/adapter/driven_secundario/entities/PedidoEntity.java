package com.techchallenge.devnet.adapter.driven_secundario.entities;

import com.techchallenge.devnet.core.domain.base.auditoria.AuditoriaDataJpa;
import com.techchallenge.devnet.core.domain.models.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.core.domain.models.enums.StatusPedidoEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Audited
public final class PedidoEntity extends AuditoriaDataJpa implements Serializable {

  public static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "status_pedido", nullable = false)
  private StatusPedidoEnum statusPedido;

  @Enumerated(EnumType.STRING)
  @Column(name = "forma_pagamento", nullable = false)
  private FormaPagamentoEnum formaPagamento;

  @Column(name = "preco_total")
  private BigDecimal precoTotal;

  @ManyToOne
  @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = true)
  private ClienteEntity cliente;

  @OneToMany(mappedBy = "pedido", cascade = {CascadeType.ALL})
  private List<ItemPedidoEntity> itensPedido = new ArrayList<>();

  @OneToOne(mappedBy = "pedido", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  private PagamentoEntity pagamento;

//  public void calcularPrecoTotal() {
//    this.getItensPedido().forEach(ItemPedido::calcularPrecoParcial);
//
//    var total = this.getItensPedido().stream()
//      .map(ItemPedido::getPrecoParcial)
//      .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//    this.setPrecoTotal(total);
//  }
}

