package br.com.usystem.stockroll.model;

import java.io.Serializable;

import lombok.Data;


@Data

public class QtdPorLocalId implements Serializable{
  
  private Local local;
  
  private Produto produto;

}
