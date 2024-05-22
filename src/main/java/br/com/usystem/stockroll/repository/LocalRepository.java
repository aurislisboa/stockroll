package br.com.usystem.stockroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.model.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {
    
}
