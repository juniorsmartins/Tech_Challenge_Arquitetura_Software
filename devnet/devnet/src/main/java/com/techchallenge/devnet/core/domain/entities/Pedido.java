package com.techchallenge.devnet.core.domain.entities;

import com.techchallenge.devnet.core.domain.base.auditoria.AuditoriaDataJpa;
import com.techchallenge.devnet.core.domain.entities.enums.FormaPagamentoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pedidos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@Audited
public final class Pedido extends AuditoriaDataJpa implements Serializable {

  public static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
  private Cliente cliente;

  @OneToMany(mappedBy = "pedido", cascade = {CascadeType.ALL})
  private List<ItemPedido> itensPedido = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  @Column(name = "forma_pagamento", nullable = false)
  private FormaPagamentoEnum formaPagamento;
}

