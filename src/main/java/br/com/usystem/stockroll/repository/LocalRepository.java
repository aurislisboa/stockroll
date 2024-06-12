package br.com.usystem.stockroll.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.model.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {
    
    @Query(value = "SELECT l FROM Local l WHERE l.id > 1")   // CD-Principal não é listado.
    List<Local> listarTodosQuiosques(); 



}
