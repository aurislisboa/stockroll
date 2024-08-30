package br.com.usystem.stockroll.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.usystem.stockroll.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

  List<Movimentacao> findByLocalId(Integer id);



  @Query(value = "SELECT p.nome_produto, SUM(m.qtd_produto) AS total "+ 
                 "FROM movimentacao AS m INNER JOIN lote AS l ON l.id_lote = m.id_lote "+
                 "INNER JOIN produto AS p ON p.id_produto = l.id_produto "+
                 "WHERE m.tipo_mov = 'Saida' "+
                 "GROUP BY p.nome_produto "+
                 "ORDER BY total DESC LIMIT 5", nativeQuery = true)
  public List<Object[]> mapProdutosMaisVendidos();


  
  @Query(value = "SELECT COUNT(*) FROM movimentacao AS m WHERE m.tipo_mov = 'Saida'", nativeQuery = true)
  public Integer totalSaidas();

  @Query(value = "SELECT SUM(mov.qtd_produto) FROM movimentacao AS mov "+
                 "INNER JOIN motivo AS mot "+
                 "ON mot.id_motivo = mov.id_motivo "+
                 "WHERE mov.tipo_mov = 'Saida' "+ 
                 "AND mot.nome_motivo = 'Descarte' "+
                 "AND mov.id_local = :localId ", nativeQuery = true)
  public Integer totalDescarte(Integer localId);



  @Query(value = "SELECT SUM(mov.qtd_produto) FROM movimentacao AS mov "+
                 "INNER JOIN motivo AS mot "+
                 "ON mot.id_motivo = mov.id_motivo "+
                 "WHERE mov.tipo_mov = 'Saida'  "+
                 "AND mot.nome_motivo = 'Descarte' ", nativeQuery = true)
  public Integer totalGeralDescarte();

}
