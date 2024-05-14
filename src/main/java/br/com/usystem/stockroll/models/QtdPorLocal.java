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


@Data


@Entity
@Table(name = "Qtd_Por_Local")
public class QtdPorLocal {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_local")
  private Local local;

  @ManyToOne
  @JoinColumn(name = "id_produto")
  private Produto produto;

  @Column(name = "qtd_min")
  private Integer minimo;

  @Column(name = "qtd_ideal")
  private Integer ideal;

}
