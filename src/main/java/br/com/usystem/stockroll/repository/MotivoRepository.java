package br.com.usystem.stockroll.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.model.Motivo;

@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Integer>{

  Optional<Motivo> findByNome(String nome);

  Boolean existsByNome(String nome);


} 