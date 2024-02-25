package br.com.usystem.stockroll.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "MovimentacaoEstoque")
public class Estoque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mov")
    private Long id;

    @Column(name = "id_usuario")
    private Long usuarioId;
    
    @Column(name = "id_produto")
    private Long produtoId;
    
    @Column(name = "data_mov")
    private LocalDateTime dataMovimentacao;

    @Column(name = "qtd_produto")
    private Integer qtdProduto;

    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @Column(name = "tipo_mov")
    private String tipoMovimentacao;

}
