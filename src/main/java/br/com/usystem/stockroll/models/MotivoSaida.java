package br.com.usystem.stockroll.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Lombok
@Data
// @AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

// JPA
@Entity
@Table(name = "Motivo_Saida")
public class MotivoSaida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_motivo")
    private Integer id;

    
    private String descricao;
}
