package br.com.usystem.stockroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.model.QtdPorLocal;
import br.com.usystem.stockroll.model.QtdPorLocalId;

@Repository
public interface QtdPorLocalRepository extends JpaRepository<QtdPorLocal, QtdPorLocalId> {

  @Query(value = "SELECT q FROM QtdPorLocal q WHERE q.local.id = ?1 AND q.produto.id = ?2")
  QtdPorLocal findByLocalAndProdutoId(Integer localId, Integer produtoId);
  
}
