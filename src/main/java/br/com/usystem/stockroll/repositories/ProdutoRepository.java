package br.com.usystem.stockroll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.usystem.stockroll.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
