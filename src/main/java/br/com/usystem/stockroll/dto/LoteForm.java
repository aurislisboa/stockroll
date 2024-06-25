package br.com.usystem.stockroll.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import br.com.usystem.stockroll.model.Produto;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteForm {
  
    private Integer id;

    @NotNull
    private Produto produto;

    @NotNull
    @Future
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate vencimento;

    @NotNull
    @Min(value = 1)
    private Integer quantidade;     

    @NotNull
    @Positive
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal valorUnitario;



}
