package br.com.usystem.stockroll.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat.Style;

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

// Lombok
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

// JPA
@Entity
@Table(name = "Movimentacao")
public class Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mov")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_local")
    private Local local;


    // @JdbcTypeCode(SqlTypes.JAVA_OBJECT)
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    
    @ManyToOne
    @JoinColumn(name = "id_lote")
    private Lote lote;
    

    @ManyToOne
    @JoinColumn(name = "id_motivo")
    private MotivoSaida motivoSaida;


    // @ManyToOne
    // @JoinColumn(name = "id_produto")
    // private Produto produto;


    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "data_mov")
    private LocalDate dataMovimentacao;


    @Column(name = "qtd_produto")
    private Integer quantidade;


    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @Column(name = "valor_unitario", nullable = true,  columnDefinition = "decimal(8,2) default '0.00'")
    private BigDecimal valorUnitario;


    @Column(name = "tipo_mov")
    private String tipoMovimentacao;


}
