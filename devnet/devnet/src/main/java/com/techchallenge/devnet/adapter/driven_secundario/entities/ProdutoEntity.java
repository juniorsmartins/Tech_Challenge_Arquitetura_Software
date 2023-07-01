package com.techchallenge.devnet.adapter.driven_secundario.entities;

import com.techchallenge.devnet.core.domain.base.auditoria.AuditoriaDataJpa;
import com.techchallenge.devnet.core.domain.models.enums.CategoriaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

@Entity
@Table(name = "produtos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Audited
public final class ProdutoEntity extends AuditoriaDataJpa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "categoria", length = 50, nullable = false)
  private CategoriaEnum categoria;

  @Column(name = "nome", length = 100, nullable = false)
  private String nome;

  @Column(name = "descricao", length = 250, nullable = false)
  private String descricao;

  @Column(name = "preco", nullable = false)
  private BigDecimal preco;
}

