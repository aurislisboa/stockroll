package br.com.usystem.stockroll.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.models.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {
    
        @Query(value = "SELECT SUM(qtd_produto) FROM `lote`", nativeQuery = true)
        Integer selectSumQtdProdutos();

        @Query(value = "SELECT SUM(valor_unitario * qtd_produto) FROM `lote`", nativeQuery = true)
        BigDecimal selectSumValorUnitario();
}
