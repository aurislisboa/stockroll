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


//Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

// JPA
@Entity
@Table(name = "Motivo")
public class Motivo {

    @Id
    @Column(name = "id_motivo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_motivo")
    private String nome;

    // private Boolean tipo;

    
    public Motivo(Integer id) {
        this.id = id;
    }

}
