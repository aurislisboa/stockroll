package br.com.usystem.stockroll.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.usystem.stockroll.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNome(String nome);

    Optional<Usuario> findByEmail(String email);

}
