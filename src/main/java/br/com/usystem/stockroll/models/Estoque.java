package br.com.usystem.stockroll.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")


@Entity
@Table(name = "Estoque_Tracking") 
public class Estoque {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_local")
  private Local local;

  @ManyToOne
  @JoinColumn(name = "id_lote")
  private Lote lote;

  @Column(name = "qtd_estoque")
  private Integer quantidade;
}
