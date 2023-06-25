package com.techchallenge.devnet.core.domain.entities;

import com.techchallenge.devnet.core.domain.entities.enums.StatusEmailEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "emails")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Audited
public final class Email implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "owner_ref")
  private String ownerRef;

  @Column(name = "email_from")
  private String emailFrom;

  @Column(name = "email_to")
  private String emailTo;

  @Column(name = "subject")
  private String subject;

  @Column(name = "text", columnDefinition = "TEXT")
  private String text;

  @Column(name = "send_data_email")
  private LocalDateTime sendDataEmail;

  @Column(name = "status_email")
  @Enumerated(EnumType.STRING)
  private StatusEmailEnum statusEmail;

  @ManyToOne
  @JoinColumn(name = "pedido_id", referencedColumnName = "id", nullable = false)
  private Pedido pedido;
}

