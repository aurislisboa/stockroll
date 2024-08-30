package br.com.usystem.stockroll.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AlertDTO {
  
  private String mensagem;
  
  private String classeCss;
  
}
