package br.com.usystem.stockroll.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode        //conferir se preciso definir o 'id'?????
@NoArgsConstructor
@AllArgsConstructor


/* Chave composta usando @Embeddable e @EmbeddedId */

@Embeddable
public class EstoqueId implements Serializable {
  
  @ManyToOne
  @JoinColumn(name = "id_local")
  private Local local;

  @ManyToOne
  @JoinColumn(name = "id_lote")
  private Lote lote; 

}
