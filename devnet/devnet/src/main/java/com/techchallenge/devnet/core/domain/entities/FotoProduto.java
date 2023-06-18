package com.techchallenge.devnet.core.domain.entities;

import com.techchallenge.devnet.core.domain.base.auditoria.AuditoriaDataJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "fotos_produtos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class FotoProduto extends AuditoriaDataJpa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "produto_id")
  private Long id;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  private Produto produto;

  @Column(name = "nome", nullable = false)
  private String nome;

  @Column(name = "descricao", nullable = false)
  private String descricao;

  @Column(name = "tipo", nullable = false)
  private String tipo;

  @Column(name = "tamanho", nullable = false)
  private long tamanho;
}

