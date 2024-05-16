package br.com.usystem.stockroll.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table
public class Lote {
    
    @Id
    @Column(name = "id_lote")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    // @Column(name = "nome_lote")
    // private String nome;

    @Column(name = "data_vencimento")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate vencimento;

    
    @Column(name = "qtd_produto", nullable = false)
    private Integer quantidade;


    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @Column(name = "valor_unitario", nullable = true,  columnDefinition = "decimal(8,2) default '0.00'")
    private BigDecimal valorUnitario;
}
