package br.com.usystem.stockroll.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@EqualsAndHashCode //acho que seria preciso definir o 'id'?????
@NoArgsConstructor
@AllArgsConstructor


@Embeddable
public class EstoqueId implements Serializable {
  
  @ManyToOne
  @JoinColumn(name = "id_local")
  private Local local;

  @ManyToOne
  @JoinColumn(name = "id_lote")
  private Lote lote;

  

}
