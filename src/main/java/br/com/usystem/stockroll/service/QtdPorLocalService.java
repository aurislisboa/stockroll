package br.com.usystem.stockroll.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.usystem.stockroll.model.Local;
import br.com.usystem.stockroll.model.Produto;
import br.com.usystem.stockroll.model.QtdPorLocal;
import br.com.usystem.stockroll.model.QtdPorLocalId;
import br.com.usystem.stockroll.repository.LocalRepository;
import br.com.usystem.stockroll.repository.QtdPorLocalRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class QtdPorLocalService {
  
  private QtdPorLocalRepository qtdPorLocalRepository; 
  private LocalRepository localRepository;




  public QtdPorLocal buscaPorLocalEProduto(Integer local, Integer produto) {
      return qtdPorLocalRepository.findByLocalAndProdutoId(local, produto);
  }


  // public void adicionaQuantidaeMinimaEIdealAoEstoque(Produto produto) {
  //       // if (produto.getQtdAtualEstoque() == null) {
  //       //     produto.setQtdAtualEstoque(0);
  //       // }            

  //       produtoRepository.save(produto);                              // salva no banco o produto que acabou se ser cadastrado.
        
  //       QtdPorLocalId CdPrincipal = new QtdPorLocalId();
  //                     CdPrincipal.setLocal(new Local(1, "CD-Principal"));  // define o id '0' que é o CD-Principal.       
  //                     CdPrincipal.setProduto(produto);      

  //       /* Adicionar uma quantidade de Mínima e Ideal */
  //       QtdPorLocal qtdPorLocal = new QtdPorLocal();                               // criar objeto do tipo QtdPorLocal.
  //                   qtdPorLocal.setId(CdPrincipal);
  //                   qtdPorLocal.setMinimo(10);
  //                   qtdPorLocal.setIdeal(100);

  //       qtdPorLocalRepository.save(qtdPorLocal);                                   // salva no banco o produto e quantidade.

  // }



  public void adicionaQuantidaeMinimaEIdeal(Produto produto) { 
    // if (produto.getQtdAtualEstoque() == null) {
    //     produto.setQtdAtualEstoque(0);
    // }                              

    //Produto produtoSalvo = produtoRepository.save(produto);                              // salva no banco o produto que acabou se ser cadastrado.

    List<Local> locais = localRepository.findAll();  
    QtdPorLocal qtdPorLocal = new QtdPorLocal();               

          for (Local local : locais) {            
            QtdPorLocalId registroId = new QtdPorLocalId(local, produto);                   // a cada Loop recebe o id local e produto.
            qtdPorLocal.setId(registroId); 
            qtdPorLocal.setMinimo(10);
            qtdPorLocal.setIdeal(100);
            // System.out.println("\n\n------------------"+ qtdPorLocal);
            qtdPorLocalRepository.save(qtdPorLocal);  // salva no banco a quantidade Mínima e Ideal para cada Local.
          }


                           
/*

    QtdPorLocalId CdPrincipal = new QtdPorLocalId();
                  CdPrincipal.setLocal(new Local(1, "CD-Principal"));  // define o id '0' que é o CD-Principal.       
                  CdPrincipal.setProduto(produto);      
*/
    /* Adicionar uma quantidade de Mínima e Ideal */
/*

    QtdPorLocal qtdPorLocal = new QtdPorLocal();                               // criar objeto do tipo QtdPorLocal.
                qtdPorLocal.setId(CdPrincipal);
                qtdPorLocal.setMinimo(10);
                qtdPorLocal.setIdeal(100);
*/
    /////////////// qtdPorLocalRepository.save(qtdPorLocal);                                   // salva no banco o produto e quantidade.
    
}










}
