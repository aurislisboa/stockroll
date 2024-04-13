package br.com.usystem.stockroll.models;

import java.util.List;

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
    private Long id;

    @Column(name="cod_barra")
    private String codigoBarra;

    @Column(name = "nome_produto")
    private String nome;

    @Column(name = "estoque_min")
    private Integer qtdMinEstoque;

    @Column(name = "estoque_ideal")
    private Integer qtdIdealEstoque;

    @Column(name = "qtd_estoque", columnDefinition = "default '0'")
    private Integer qtdAtualEstoque;



    // @OneToMany(mappedBy = "produto")
    // private List<Estoque> estoque;
}
