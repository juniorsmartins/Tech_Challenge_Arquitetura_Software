package com.techchallenge.devnet.interface_adapters.driven_secundario.daos;

import com.techchallenge.devnet.enterprise_business_rules.base.auditoria.AuditoriaDataJpa;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
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
import java.util.UUID;

@Entity
@Table(name = "pedidos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Audited
public final class PedidoDao extends AuditoriaDataJpa implements Serializable {

  public static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "codigo", unique = true, nullable = false)
  private UUID codigo;

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
  private ClienteDao cliente;

  @OneToMany(mappedBy = "pedido", cascade = {CascadeType.ALL})
  private List<ItemPedidoDao> itensPedido = new ArrayList<>();

  @OneToOne(mappedBy = "pedido", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  private PagamentoDao pagamento;
}

