package com.techchallenge.devnet.core.domain.entities;

import com.techchallenge.devnet.core.domain.base.auditoria.AuditoriaDataJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;

import java.io.Serializable;

@Entity
@Table(name = "itens")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@Audited
public final class Item extends AuditoriaDataJpa implements Serializable {

  public static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "pedido_id", nullable = false)
  private Pedido pedido;

  @ManyToOne
  @JoinColumn(name = "produto_id", nullable = false)
  private Produto produto;

  @Column(name = "quantidade", nullable = false)
  private int quantidade;
}
