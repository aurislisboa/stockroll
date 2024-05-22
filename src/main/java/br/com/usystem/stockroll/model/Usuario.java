package br.com.usystem.stockroll.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


// Lombok
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

// JPA
@Entity
@Table
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nome;

    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Perfil perfil;

    //@DateTimeFormat(iso = ISO.DATE) 
    private LocalDateTime cadastro;




    // @OneToMany(mappedBy = "usuario")
    // private List<Estoque> estoque;

}
