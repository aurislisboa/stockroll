package br.com.usystem.stockroll.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
// @Table(name = "Estoque")
public class Estoque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mov")
    private Long id;

    // @JdbcTypeCode(SqlTypes.JAVA_OBJECT)
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    // @JdbcTypeCode(SqlTypes.JAVA_OBJECT)
    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;


    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "data_mov")
    private LocalDate dataMovimentacao;

    @Column(name = "qtd_produto")
    private Integer quantidade;

    @Column(name = "valor_unitario", nullable = false,  columnDefinition = "decimal(8,2) default '0.00'")
    private BigDecimal valorUnitario;

    @Column(name = "tipo_mov")
    private String tipoMovimentacao;

}
