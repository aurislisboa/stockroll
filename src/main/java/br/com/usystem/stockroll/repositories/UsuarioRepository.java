package br.com.usystem.stockroll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.usystem.stockroll.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
