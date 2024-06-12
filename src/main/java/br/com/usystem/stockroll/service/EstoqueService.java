package br.com.usystem.stockroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.usystem.stockroll.model.Estoque;
import br.com.usystem.stockroll.repository.EstoqueRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EstoqueService {

  private EstoqueRepository estoqueRepository;



  public Estoque salvarEstoque(Estoque estoque) {
    return estoqueRepository.save(estoque);
  }

  public List<Estoque> listaLotesDoCdPrincipal() {
    return estoqueRepository.listaLotesDoCdPrincipal();
  }

  public Estoque buscarLoteDoCdPrincipalPorId(Integer loteId) {
    return estoqueRepository.buscarLoteDoCdPrincipalPorId(loteId);
  }

  public Double selectSumQtdProdutosById(Integer produtoId) {
    return estoqueRepository.selectSumQtdProdutosById(produtoId);
  }

  public Integer totalEmEstoquePorLocalId(Integer localId) {
    return estoqueRepository.selectSumAndGroupById(localId);
  }

  public Integer totalDeSaidasDeProdutos(Integer localId) {
    return estoqueRepository.totalDeSaidasDeProdutos(localId);
  }

  public List<Estoque> buscarPorLocalEProduto(Integer local, Integer produto) {
    return estoqueRepository.findByLocalAndProduto(local, produto);
  }

  public List<Estoque> buscarTodosProdutosEmEstoque() {
    return estoqueRepository.findAllEstoqueMaiorQueZero();
  }

  public List<Estoque> buscarProdutosEmEstoquePorLocal(Integer localId) {
    return estoqueRepository.findAllEstoquePorLocalMaiorQueZero(localId);
  }





   /* GERA O ALERTA NA MÃO - esse código deve consomir mais processamento */
    // puxar o total do estoque Maior que 0 (ok)
    // puxar o total mínimo (ok)
    // comparar se é menor ou igual ()
    // pegar o nome do produto ()
    // exibir no alerta ()
    // nome --- qtd ()

/*
    public List<String> verficaStatusDoEstoque() {

    //------- fazendo uma busca sobre cada objeto ------//
    // for (Estoque produto : estoque) {
    // if(produto.getQuantidade() > 0) {
    // System.out.println(produto.getQuantidade() +"\n");
    // }
    // }

    List<Estoque> estoque = estoqueRepository.findAllEstoqueMaiorQueZero();
    List<QtdPorLocal> qtdPorLocal = qtdPorLocalRepository.findAll();
    
    int id_produto;
    int id_qtd;
    List<String> alerta = new ArrayList<>();

    for (Estoque produto : estoque) {
      id_produto = produto.getId().getLote().getId();

      for (QtdPorLocal qtd : qtdPorLocal) {
        id_qtd = qtd.getId().getProduto().getId();

        if (id_produto == id_qtd) {
          if (produto.getQuantidade() <= qtd.getMinimo()) {
            // System.out.println("\nO id: "+ id_produto + " é igual a o id: " + id_qtd);
            // System.out.println("Nome: " +
            // produto.getId().getLote().getProduto().getNome());
            // System.out.println("Qtd: " + produto.getQuantidade());

            alerta.add(produto.getId().getLote().getProduto().getNome());

          }
        }
      }
    }

    return alerta;
  }//End
*/





  /*
   * 
   * Produto_Id: 1 | Qtd: 100
   * Produto_Id: 2 | Qtd: 400
   * Produto_Id: 3 | Qtd: 400
   * Produto_Id: 5 | Qtd: 500
   * Produto_Id: 2 | Qtd: 50
   * Produto_Id: 5 | Qtd: 25
   * 
   * 
   * Qtd Por Local:
   * 
   * 
   * Produto_Id: 1 | Qtd Mínima: 200
   * Produto_Id: 24 | Qtd Mínima: 30
   * Produto_Id: 25 | Qtd Mínima: 10
   * 
   */








}//End-Class