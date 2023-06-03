package com.techchallenge.devnet.core.domain.entities;

import com.techchallenge.devnet.core.domain.base.auditoria.AuditoriaDataJpa;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
import java.util.HashSet;
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
  @JoinColumn(name = "cliente_id", nullable = true)
  private Cliente cliente;

  @OneToMany(mappedBy = "pedido", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<ItemPedido> itens = new HashSet<>();

//  @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
//  @JoinTable(name = "itens",
//    joinColumns = {@JoinColumn(name = "pedido_id", referencedColumnName = "id")},
//    inverseJoinColumns = {@JoinColumn(name = "produto_id", referencedColumnName = "id")})
//  private Set<Produto> produtos = new HashSet<>();
}

