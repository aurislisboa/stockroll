package br.com.usystem.stockroll.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.model.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {
    

        // (excluir) essa consulta porque já fiz uma parecida em EstoqueRepository usando JPQL.
        @Query(value = "SELECT SUM(e.qtd_estoque) FROM estoque_tracking AS e INNER JOIN lote AS l ON e.id_lote = l.id_lote WHERE e.qtd_estoque > 0", nativeQuery = true)
        Integer selectSumQtdProdutos();



        @Query(value = "SELECT SUM(e.qtd_estoque * l.valor_unitario) FROM estoque_tracking AS e INNER JOIN lote AS l ON e.id_lote = l.id_lote WHERE e.qtd_estoque > 0", nativeQuery = true)
        BigDecimal selectSumValorUnitario();


        // List<Lote> findByQuantidadeGreaterThan(int quantidade);         // está funcionando.

   
        
        
}
