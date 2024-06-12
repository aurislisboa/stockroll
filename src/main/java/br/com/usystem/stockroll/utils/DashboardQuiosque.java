package br.com.usystem.stockroll.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardQuiosque {

  private Integer id;
  private String nome;
  private Integer totalEstoque;
  private Integer totalSaida;
  private Integer totalAlerta;

}
