package br.com.usystem.stockroll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.usystem.stockroll.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query(value = "SELECT SUM(qtd_estoque) FROM `produto`", nativeQuery = true)
        Integer selectSumQtdEstoque();
}
