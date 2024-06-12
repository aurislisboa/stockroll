package br.com.usystem.stockroll.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table(name = "Qtd_Por_Local")
public class QtdPorLocal {
  

  
  @EmbeddedId
  private QtdPorLocalId id;
 


  @Column(name = "qtd_min")
  private Integer minimo;

  @Column(name = "qtd_ideal")
  private Integer ideal;


}
