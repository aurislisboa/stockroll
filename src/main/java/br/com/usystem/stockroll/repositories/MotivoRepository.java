package br.com.usystem.stockroll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.models.Motivo;

@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Integer>{

} 