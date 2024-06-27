package br.com.usystem.stockroll.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotivoForm {

  
  private Integer id;

  @NotNull
  @Size(min = 4, max = 100)
  private String nome;

  
}
