package br.com.usystem.stockroll.model;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")


/* Chave composta usando @Embeddable e @EmbeddedId */

@Entity
@Table(name = "Estoque_Tracking") 
public class Estoque {

  
  @EmbeddedId
  private EstoqueId id;

  @Column(name = "qtd_estoque")
  private Integer quantidade;
}
