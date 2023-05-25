package com.techchallenge.devnet.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "clientes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@Audited
@SQLDelete(sql = "UPDATE clientes SET deletado = true WHERE id = ?")
@Where(clause = "deletado = false")
public final class Cliente implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "codigo")
  private UUID codigo;

  @Column(name = "nome", length = 100)
  private String nome;

  @Column(name = "cpf")
  private String cpf;

  @Column(name = "email", length = 100)
  private String email;

  @Column(name = "deleted")
  private Boolean deletado;
}

