package br.com.usystem.stockroll.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import jakarta.persistence.*;
import lombok.*;

// Lombok
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

// JPA
@Table
@Entity
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Integer id;

    @Column(name="cod_barra")
    private String codigoBarra;

    @Column(name = "nome_produto")
    private String nome;

    // @Column(name = "qtd_estoque", columnDefinition = "default '0'")
    // private Integer qtdAtualEstoque;
    
    // @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    // @Column(name = "valor_unitario", nullable = false,  columnDefinition = "decimal(8,2) default '0.00'")
    // private BigDecimal valorUnitario;


    // @OneToMany(mappedBy = "produto")
    // private List<Estoque> estoque;
}
