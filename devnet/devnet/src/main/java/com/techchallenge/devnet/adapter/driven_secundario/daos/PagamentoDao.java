package com.techchallenge.devnet.adapter.driven_secundario.daos;

import com.techchallenge.devnet.core.domain.base.auditoria.AuditoriaDataJpa;
import com.techchallenge.devnet.core.domain.models.enums.StatusPagamentoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

@Entity
@Table(name = "pagamentos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Audited
public final class PagamentoDao extends AuditoriaDataJpa implements Serializable {

  public static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "status_pagamento", nullable = false)
  private StatusPagamentoEnum statusPagamento = StatusPagamentoEnum.ABERTO;

  @OneToOne
  @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
  private PedidoDao pedido;

  @Column(name = "nome_imagem_qrcode", nullable = true)
  private String nomeImagemQRCode;
}

