package br.com.usystem.stockroll.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.models.Estoque;
import br.com.usystem.stockroll.models.EstoqueId;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, EstoqueId> {

  List<Estoque> findByQuantidadeGreaterThan(int quantidade);   // retorna uma lista de produtos com quantidade Maior que 0. 
  
  // select m.id, m.nome, m.crm, e.descricao
  // from Medico m
  // inner join m.especialidade e     

  // SELECT h FROM Historico h WHERE h.quantidade > 0 ORDER BY h.idhistorico DESC

  @Query(value = "SELECT e  \r\n" + //
                 "FROM Estoque e \r\n" + //
                 "WHERE e.quantidade > 0")
  public List<Estoque> findAllEstoqueMaiorQueZero();

  @Query(value = "SELECT e FROM Estoque e WHERE e.quantidade > 0 ORDER BY e.id.lote.produto.nome ASC")
  public List<Estoque> findAllEstoqueMaiorQueZeroAndOrderByProduto();


}
