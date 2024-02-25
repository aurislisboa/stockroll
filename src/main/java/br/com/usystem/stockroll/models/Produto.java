package br.com.usystem.stockroll.models;

import jakarta.persistence.*;
import lombok.*;

// Lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString

// JPA
@Table
@Entity
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long id;

    @Column(name="cod_barra")
    private String codigoBarra;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "estoque_min")
    private Integer qtdMinEstoque;

    @Column(name = "estoque_ideal")
    private Integer qtdIdealEstoque;

    @Column(name = "qtd_estoque")
    private Integer qtdAtualEstoque;
}
