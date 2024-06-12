package br.com.usystem.stockroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.model.QtdPorLocal;
import br.com.usystem.stockroll.model.QtdPorLocalId;

@Repository
public interface QtdPorLocalRepository extends JpaRepository<QtdPorLocal, QtdPorLocalId> {

  /*  Consulta usando Chave composta  @IdClass  */
  // @Query(value = "SELECT q FROM QtdPorLocal q WHERE q.local.id = ?1 AND q.produto.id = ?2")
  // QtdPorLocal findByLocalAndProdutoId(Integer localId, Integer produtoId);


  // cheguei a fazer essa mesma consulta na Classe EstoqueRepository.
  // provavelmente com a pesquisa de lá e comparando com essa dê para comparar e gerar o alerta (estoque <= mínimo).
  @Query(value = "SELECT q FROM QtdPorLocal q WHERE q.id.local.id = ?1 AND q.id.produto.id = ?2")
  QtdPorLocal findByLocalAndProdutoId(Integer localId, Integer produtoId);


  
}
