package com.techchallenge.devnet.interface_adapters.driven_secundario.daos;

import com.techchallenge.devnet.enterprise_business_rules.base.auditoria.AuditoriaDataJpa;
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
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Audited
public final class ClienteDao extends AuditoriaDataJpa implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "nome", length = 100, nullable = false)
  private String nome;

  @Column(name = "cpf", length = 14, nullable = false, unique = true)
  private String cpf;

  @Column(name = "numero_telefone", length = 11, nullable = true)
  private String numeroTelefone;

  @Column(name = "email", length = 100, nullable = false)
  private String email;

  @Column(name = "data_nascimento", nullable = false)
  private LocalDate dataNascimentoLocalDate;
}

