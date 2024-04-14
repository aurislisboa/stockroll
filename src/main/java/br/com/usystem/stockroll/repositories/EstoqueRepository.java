package br.com.usystem.stockroll.repositories;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.models.Estoque;
import br.com.usystem.stockroll.models.Produto;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    // Produto findProdutoById(Long produtoId);
    
}
