package br.com.usystem.stockroll.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.usystem.stockroll.model.Estoque;
import br.com.usystem.stockroll.model.EstoqueId;
import br.com.usystem.stockroll.model.Local;
import br.com.usystem.stockroll.model.Lote;
import br.com.usystem.stockroll.model.Motivo;
import br.com.usystem.stockroll.model.Movimentacao;
import br.com.usystem.stockroll.model.Usuario;
import br.com.usystem.stockroll.repository.EstoqueRepository;
import br.com.usystem.stockroll.repository.MovimentacaoRepository;


@Service
public class MovimentacaoService {


    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired 
    private EstoqueRepository estoqueRepository; 

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EstoqueService estoqueService;
  




    public Integer totalSaidas() {
        return movimentacaoRepository.totalSaidas();
    }


    public List<Movimentacao> buscarTodos() {
            //     Collections.reverse(movimentacao);                       // outra forma de fazer.
        return movimentacaoRepository.findAll(Sort.by("id").descending());
    }


    public List<Movimentacao> buscarLocalPorId(Integer local) {
        return movimentacaoRepository.findByLocalId(local);
    }


    public Movimentacao buscarPorId(Long id) {
        return movimentacaoRepository.getReferenceById(id);
    }


    public Integer totalDesperdicio(Integer localId) {
        return movimentacaoRepository.totalDescarte(localId);
    }


    public Integer totalGeralDescarteProdutos() {
        return movimentacaoRepository.totalGeralDescarte();
    }



    public List<Map<String, Object>> mapProdutosMaisVendidos() {
        List<Object[]> results = movimentacaoRepository.mapProdutosMaisVendidos();
        return results.stream().map(result -> {
            Map<String, Object> map = new HashMap<>();
            map.put("nomeProduto", result[0]);
            map.put("total", result[1]);
            return map;
        }).collect(Collectors.toList());
    }



    /* 
     * Valida se o usuário digitou um valor maior do que existe em Estoque.
     */
    public boolean isQtdSuperiorEmEstoque(Lote lote, Integer quantidade) {
        var qtdEmEstoque = estoqueService.buscarLoteDoCdPrincipalPorId(lote.getId()).getQuantidade();
        if(quantidade > qtdEmEstoque) { return true; }
        else { return false; }
      }
  


// ------------------------------------------------------------------------------------------//


    /* 
     *  Serviço responsável por:
     *  - Cadastrar um produto na tabela Lote,
     *  - Criar um registro na tabela Movimentação e
     *  - Cadastrar um novo lote na tabela Estoque. 
     */

  public Movimentacao cadastrarMovimentacao(Lote lote, Principal principal) {   // Recebe os dados do lote e o Usuário logado
    
            /*  confere se existe um usuário logado no sistema */
            // if (principal == null) {
            //     // implementar uma Exception para variável Nula!
            //     throw new NullPointerException("O usuário não foi encontrado!");
            // }

            /*  confere se existe um Lote */
            // if (lote == null) {
            //     // implementar uma Exception para variável Nula!
            //     throw new NullPointerException("O lote não existe!");
            // }


            /*  Primeiro é salvo o Lote  */

//*******************           loteRepository.save(lote);          // cadastra um novo lote na tabela Lote.
            
        /*
        *  Depois é definido os campos pora inserir uma Movimentação de Entrada no CD - Principal.
        *  O código abaixo é uma cópia descarada da Action 'cadastrar' de MovimentacaoController
        *  'movimentacao/cadastrar/entrada'
        */

        Usuario usuario = usuarioService.getUsuarioLogadoNoSistema(principal);      // obtém o usuário logado.
        Movimentacao movimentacao = new Movimentacao();
                movimentacao.setUsuario(usuario);
                movimentacao.setDataMovimentacao(LocalDateTime.now());
                movimentacao.setTipoMovimentacao("Entrada");
                movimentacao.setMotivo(new Motivo(1));        
                movimentacao.setLocal(new Local(1, "CD-Principal"));
                movimentacao.setValorUnitario(lote.getValorUnitario());
                movimentacao.setLote(lote);                                         // lote que acabou de ser cadastrado.
                movimentacao.setQuantidade(lote.getQuantidade());                   // quantidade de produtos do lote que acabou de ser cadastrado.
   
            movimentacaoRepository.save(movimentacao);

            /*  Cadastrar o Lote na tabela Estoque  */
            this.adicionaEstoqueCdPrincipal(movimentacao);

            return movimentacao;
    } //End



    // ------------------------------------------------------------------------------------------//


    /* SERVIÇO USADO PELO BOTÃO 'TRANSFERIR' DA VIEW LOTES  */


    public Movimentacao cadastrarEntradaNoQuiosqueViaTransferencia(Movimentacao movimentacao, Principal principal) {
    // System.out.println("\n\n\n\n================" + movimentacao);
        Usuario usuario = usuarioService.getUsuarioLogadoNoSistema(principal);  // obtém o usuário logado no sistema.
        
        movimentacao.setId(null);                                            // Cria sempre um novo registro e não permite atualização do 'id'.
        movimentacao.setUsuario(usuario);                                       // usuário que fez a movimentação.
        movimentacao.setDataMovimentacao(LocalDateTime.now());                  // data que foi feita a movimentação.
        movimentacao.setTipoMovimentacao("Transferência");           // trava como uma Transferência.
        movimentacao.setMotivo(new Motivo(1));                               // trava como 'Saída' porque 
        Lote lote = movimentacao.getLote();                                     // Captura informações do Lote desta Movimentação.
        movimentacao.setValorUnitario(lote.getValorUnitario());                 // (remover) retorna o preço desse produto e copia para preço na Movimentação. 

        //this.diminuiQuantidadeNoLote(movimentacao);                             // Diminui a quantidade de produtos na tabela 'Lote'.
        this.diminuiEstoqueCdPrincipal(movimentacao);
        this.adicionaEstoqueQuiosque(movimentacao);
        
        return movimentacaoRepository.save(movimentacao);
    } //End



    public Movimentacao cadastrarSaidaDoQuiosque(Movimentacao movimentacao, Principal principal) {

        Usuario usuario = usuarioService.getUsuarioLogadoNoSistema(principal);
        
        movimentacao.setId(null);
        movimentacao.setUsuario(usuario);
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        movimentacao.setTipoMovimentacao("Saida");
        Lote lote = movimentacao.getLote();        
        movimentacao.setValorUnitario(lote.getValorUnitario());
        // Long produtoId = movimentacao.getProduto().getId();
        // Produto produto = produtoRepository.getReferenceById(produtoId);
            // produto.setQtdAtualEstoque(produto.getQtdAtualEstoque() - movimentacao.getQuantidade());
        // movimentacao.setPreco(produto.getValorUnitario());
        
        
        this.diminuiEstoqueQuiosque(movimentacao);               // método interno da classe.        
        
        return movimentacaoRepository.save(movimentacao);

    }//End






    // ------------------------------ MÉTODOS INTERNOS DESTA CLASSE --------------------------------- //

    public EstoqueId procuraEstoqueId(Movimentacao movimentacao) {
        EstoqueId estoqueId = new EstoqueId();                                      // inicializa o Id composto de duas chaves estrangeiras (id_local, id_lote)
                  estoqueId.setLocal(movimentacao.getLocal());                      // define o local da movimentação atual.
                  estoqueId.setLote(movimentacao.getLote());                        // define o lote  da movimentação atual.
        return estoqueId;
    }


    public EstoqueId defineComoCdPrincipal(Movimentacao movimentacao) {
        EstoqueId estoqueId = new EstoqueId();                                          // inicializa o Id composto de duas chaves estrangeiras (id_local, id_lote)
                  estoqueId.setLocal(new Local(1, "CD-Principal"));             // somente o CD-Principal.
                  estoqueId.setLote(movimentacao.getLote());                            // define o lote  da movimentação atual.
        return estoqueId;
    }//End



    private void adicionaEstoqueCdPrincipal(Movimentacao movimentacao) {
        EstoqueId estoqueId = this.defineComoCdPrincipal(movimentacao);
        Integer quantidade = movimentacao.getQuantidade();

        this.adicionaQuantidade(estoqueId, quantidade);
    }


    private void diminuiEstoqueCdPrincipal(Movimentacao movimentacao) {
        EstoqueId estoqueId = this.defineComoCdPrincipal(movimentacao);
        Integer quantidade = movimentacao.getQuantidade();

        this.diminuiQuantidade(estoqueId, quantidade);
    }//End


    private void adicionaEstoqueQuiosque(Movimentacao movimentacao) {
        EstoqueId estoqueId = this.procuraEstoqueId(movimentacao);
        Integer quantidade = movimentacao.getQuantidade();

        this.adicionaQuantidade(estoqueId, quantidade);
    }//End


    private void diminuiEstoqueQuiosque(Movimentacao movimentacao) {
        EstoqueId estoqueId = this.procuraEstoqueId(movimentacao);
        Integer quantidade = movimentacao.getQuantidade();

         this.diminuiQuantidade(estoqueId, quantidade);
    }//End



    private void adicionaQuantidade(EstoqueId estoqueId, Integer quantidade) {
        if(!estoqueRepository.existsById(estoqueId)) {                              // se não existir um Estoque(id_local, id_lote)
            /* Cadastra um novo registro na tabela Estoque com a quantidade do Quiosque Atual */
            Estoque estoque = new Estoque();
                    estoque.setId(estoqueId);                                       // recebe a chave estrangeira (id_local, id_lote)
                    estoque.setQuantidade(quantidade);                              // define a quantidade para o Quiosque. 
            estoqueRepository.save(estoque);
        
        } else {
            /* Adiciona a quantidade no Quiosque Atual caso ele já exista. */                
            Estoque estoque = estoqueRepository.getReferenceById(estoqueId);            // recupera os dados deste 'lote' no 'Quiosque' escolhido
            Integer soma = estoque.getQuantidade() + quantidade;                        // soma (+) a quantidade do Estoque. 
                           estoque.setQuantidade(soma);                                 // atualiza o Quiosque com a nova quantidade.
                          //estoqueRepository.save(estoque);                            <---- provavelmente esteja faltando o save.
        }
    }//End


    /* Diminuição do Estoque - Reaproveitamento de código para evitar repetição */
    private void diminuiQuantidade(EstoqueId estoqueId, Integer quantidade) {
        Estoque estoque = estoqueRepository.getReferenceById(estoqueId);            // recupera os dados do Estoque escolhido.
        Integer diferenca = estoque.getQuantidade() - quantidade;                   // subtrai (-) a quantidade do Estoque. 
                            estoque.setQuantidade(diferenca);                       // atualiza o Quiosque com a nova quantidade.
                //estoqueRepository.save(estoque);                                     <---- provavelmente esteja faltando o save.
    }//End




    // private void diminuiQuantidadeNoLote(Movimentacao movimentacao) {
            
    //     /* Diminui a quantidade de produtos na tabela 'Lote' */
    //     Lote lote = movimentacao.getLote();                                       // obtém informações desse lote.     
    //     Integer diferenca = lote.getQuantidade() - movimentacao.getQuantidade();  // subtrai da quantidade do 'Lote' o total informado pelo usuário nessa 'transferência'.
    //                         lote.setQuantidade(diferenca);                        // define o novo total de produtos no 'Lote'.        
    // }//End




    public void editarQuantidadeCdPrincipal(Estoque estoque) {
            // EstoqueId estoqueId = new EstoqueId(new Local(1, "CD-Principal"), lote);
            // Estoque estoque = estoqueRepository.findById(estoqueId).orElseThrow();
            // Integer quantidadeEstoque = estoque.getQuantidade();

            // System.out.println("\n\n#############################\n\n" + estoque);

    }
        // pega a quantidade atual do estoque
        // pega o valor a nova quantidade digitada
        // se for maior que a quantidade do estoque
            // adiciono ao estoque
        // se não 
            // exclui do estoque












} //End-Class
