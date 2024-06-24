package br.com.usystem.stockroll.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.model.Estoque;
import br.com.usystem.stockroll.model.EstoqueId;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, EstoqueId> {


  List<Estoque> findByQuantidadeGreaterThan(int quantidade);   // Será preciso agrupar pelo nome do produto. 


  @Query(value = "SELECT e FROM Estoque e WHERE e.quantidade > 0")
  public List<Estoque> findAllEstoqueMaiorQueZero();   

  @Query(value = "SELECT e FROM Estoque e WHERE e.id.local.id = :localId AND e.quantidade > 0")
  public List<Estoque> findAllEstoquePorLocalMaiorQueZero(Integer localId);
  
  @Query(value = "SELECT e FROM Estoque e WHERE e.quantidade > 0 ORDER BY e.id.lote.produto.nome ASC")
  public List<Estoque> findAllEstoqueMaiorQueZeroAndOrderByProduto();


  @Query(value = "SELECT e FROM Estoque e WHERE e.id.local.id = ?1 AND e.quantidade > 0 ORDER BY e.id.lote.produto.nome ASC")  
  public List<Estoque> procuraPeloLocalComQuantidadeMaiorQueZeroEOrdenaPeloNomeDoProduto(Integer id);


  @Query("SELECT e FROM Estoque e WHERE e.id.local.id = 1 AND e.quantidade > 0")
  List<Estoque> listaLotesDoCdPrincipal();


  @Query("SELECT e FROM Estoque e WHERE e.id.local.id = 1 AND e.id.lote.id = :id")  
  Estoque buscarLoteDoCdPrincipalPorId(Integer id);       // (remover) mudar esse nome para buscarNoCdPrincipalLotePorId()


  @Query(value = "SELECT e FROM Estoque e WHERE e.id.local.id = ?1 AND e.id.lote.id = ?2")
  Estoque findByLocalAndLoteId(Integer localId, Integer loteId); 
  

  @Query(value = "SELECT e FROM Estoque e WHERE e.id.local.id = :localId AND e.id.lote.produto.id = :produtoId AND e.quantidade > 0")
  List<Estoque> findByLocalAndProduto(Integer localId, Integer produtoId);

  
  @Query(value = "SELECT SUM(e.qtd_estoque) FROM estoque_tracking AS e WHERE e.id_local = :id AND e.qtd_estoque > 0", nativeQuery = true)
  Double selectSumQtdProdutosById(Integer id);

  // com JPQL usando a cláusula WHERE.
  @Query(value = "SELECT SUM(e.quantidade) FROM Estoque e WHERE e.id.local.id = :localId")    
  Integer selectSumAndGroupById(Integer localId);

  // mudar para saída por dia.
  @Query(value = "SELECT COUNT(*) FROM movimentacao WHERE id_local = :localId AND tipo_mov = 'Saida'", nativeQuery = true) 
  Integer totalDeSaidasDeProdutos(Integer localId);

  


  // ERRO!
  // @Query(value = "SELECT e, SUM(e.quantidade) FROM Estoque e GROUP BY e.id.local.id") 
  // List<Estoque> selectSumAndGroupById();

//retornou essa lista sem somar as quantidades.
//  [
// 	Estoque( id=EstoqueId( local=Local(id=1, nome=CD - Principal), lote=Lote(id=1, produto=Produto(id=2, codigoBarra=0000000000002, nome=Leite Pirancajuba Caixa 1l), vencimento=2010-10-10, quantidade=100, valorUnitario=1.00)), quantidade=20), 
// 	Estoque( id=EstoqueId( local=Local(id=2, nome=Tatuapé),        lote=Lote(id=1, produto=Produto(id=2, codigoBarra=0000000000002, nome=Leite Pirancajuba Caixa 1l), vencimento=2010-10-10, quantidade=100, valorUnitario=1.00)), quantidade=20),
// 	Estoque( id=EstoqueId( local=Local(id=3, nome=Aricanduva),     lote=Lote(id=1, produto=Produto(id=2, codigoBarra=0000000000002, nome=Leite Pirancajuba Caixa 1l), vencimento=2010-10-10, quantidade=100, valorUnitario=1.00)), quantidade=20)
//  ]



// funcionou usando o GROUP BY.
// retornou a soma das quantidades  ----> 70, 20, 20
// @Query(value = "SELECT SUM(e.quantidade) FROM Estoque AS e GROUP BY e.id.local.id") 
// List<Long> selectSumAndGroupById();





}
