package br.com.usystem.stockroll.repositories;

import java.math.BigDecimal;
import java.util.List;

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

        List<Lote> findByQuantidadeGreaterThan(int quantidade);         // está funcionando.



        // @Query(value = "SELECT lote.id_lote, lote.id_produto, lote.data_vencimento, lote.qtd_produto, estoque_tracking.qtd_estoque, lote.valor_unitario  \r\n" + //
        // "FROM lote \r\n" + //
        // "INNER JOIN estoque_tracking ON lote.id_lote = estoque_tracking.id_lote \r\n" + //
        // "WHERE qtd_estoque > 0", nativeQuery = true)
        // List<Lote> findAllLoteJoinEstoque();

        // select m.id, m.nome, m.crm, e.descricao
        // from Medico m
        // inner join m.especialidade e     

        // @Query(value = "select l.id, l.produto, l.vencimento  \r\n" + //
        // "from Lote l \r\n" + //
        // "inner join  l.Estoque ON Lote.id = Estoque.EstoqueId.Lote.id \r\n" + //
        // "WHERE Estoque.quantidade > 0")
        // List<Lote> findAllLoteJoinEstoque();
        
}
