package br.com.usystem.stockroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.model.Motivo;

@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Integer>{

} 