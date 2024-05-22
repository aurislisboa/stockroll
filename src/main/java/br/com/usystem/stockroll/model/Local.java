package br.com.usystem.stockroll.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")


@Entity
@Table(name = "Local_Estoque")
public class Local {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_local")
    private Integer id;

    @Column(name = "nome_local")
    private String nome;
}
