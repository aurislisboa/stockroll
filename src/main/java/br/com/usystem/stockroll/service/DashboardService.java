package br.com.usystem.stockroll.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.usystem.stockroll.model.Estoque;
import br.com.usystem.stockroll.model.Local;
import br.com.usystem.stockroll.model.QtdPorLocal;
import br.com.usystem.stockroll.utils.DashboardQuiosque;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service  
public class DashboardService {
  
  private EstoqueService estoqueService;  
  private LocalService localService;
  private QtdPorLocalService qtdPorLocalService;



  public List<DashboardQuiosque> resumoDosQuiosques() {
      List<DashboardQuiosque> lista = new ArrayList<>();
      List<Local> locais = localService.listarTodosQuiosques(); // retorna todos os quiosques.

      /* Popula o Objeto DashboardQuiosque */
      for (Local local : locais) {
          Integer contador = 0;
          Integer localId  = local.getId();
          DashboardQuiosque dashQuiosque = new DashboardQuiosque();    
          dashQuiosque.setId(localId);          
          dashQuiosque.setNome(local.getNome());
          dashQuiosque.setTotalEstoque(estoqueService.totalEmEstoquePorLocalId(localId));         // busca pelo id do quiosque.
          dashQuiosque.setTotalSaida(estoqueService.totalDeSaidasDeProdutos(localId));            // devo mudar para saída por dia.
                for (Estoque estoque : estoqueService.buscarProdutosEmEstoquePorLocal(localId)) { // retorna todos os produtos procurando pelo local informado.
                        Integer produtoId = estoque.getId().getLote().getProduto().getId();             // qual o id desse produto.
                        QtdPorLocal qtdPorLocal = qtdPorLocalService.buscaPorLocalEProduto(localId, produtoId); // faz uma consulta na tabela que guarda o mínimo para cada produto.
                            if(estoque.getQuantidade() <= qtdPorLocal.getMinimo()) {            // se estive abaixo do mínimo conta alerta.
                                contador++;  
                            }
                }// for
          dashQuiosque.setTotalAlerta(contador); 
          lista.add(dashQuiosque);                  // cria um perfil para cada quiosque.
      }// for

     return lista;
  } //End



  /* método responsável por gerar informações para o sininho de alerta */
  // recupera quantos produtos tem no estoque,  a quantidade de um produto em estoque
  // comparar com o mínimo em qtd_por_local
  public List<String> alertaMinimoEmEstoque() {
    List<String> sininhoAlerta = new ArrayList<>();                                         // armazena os produtos para exibir no sininho.
    List<Local> locais = localService.listarTodosLocais();                                  // retorna todos os locais de estoque.
        for (Local local : locais) {                                                        // itera sobre cada estoque.
            for (Estoque estoque : estoqueService.buscarProdutosEmEstoquePorLocal(local.getId())) {     // retorna os produtos em cada local.
                    Integer localId = estoque.getId().getLocal().getId();
                    Integer produtoId = estoque.getId().getLote().getProduto().getId();
                    String nomeProduto = estoque.getId().getLote().getProduto().getNome();
                    Integer qtdEstoque = estoque.getQuantidade();

                    QtdPorLocal qtdPorLocal = qtdPorLocalService.buscaPorLocalEProduto(localId, produtoId);     // cada local tem sua quantidade mínima para cada produto.
                    Integer qtdMinima = qtdPorLocal.getMinimo();                                                // recupera o minimo para esse produto.


                    if(qtdEstoque <= qtdMinima) {                                                   // se estive abaixo do mínimo exbie um alerta.
                        // System.out.println("\n ==================== \n\n"+qtdEstoque +" "+ nomeProduto+" "+localId);
                        sininhoAlerta.add(" "+ qtdEstoque +" -  "+ nomeProduto +" - "+ estoque.getId().getLocal().getNome()); 
                    }        
            }
        }
        return sininhoAlerta;
  }//End

  



}//End-Class
