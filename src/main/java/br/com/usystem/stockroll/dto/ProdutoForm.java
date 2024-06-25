package br.com.usystem.stockroll.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoForm {

  
  private Integer id;

  @NotNull
  // @Max(13)                    // tamanho máximo de um código de barras
  @Size(min = 8, max = 13)
  private String codigoBarra;

  @NotNull
  @Size(min = 2, max = 150)   // reduzir tamanho na tabela do banco.
  private String nome;
  
}
