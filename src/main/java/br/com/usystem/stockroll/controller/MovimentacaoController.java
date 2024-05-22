package br.com.usystem.stockroll.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.model.Estoque;
import br.com.usystem.stockroll.model.EstoqueId;
import br.com.usystem.stockroll.model.Local;
import br.com.usystem.stockroll.model.Lote;
import br.com.usystem.stockroll.model.Motivo;
import br.com.usystem.stockroll.model.Movimentacao;
import br.com.usystem.stockroll.model.Perfil;
import br.com.usystem.stockroll.model.Produto;
import br.com.usystem.stockroll.model.Usuario;
import br.com.usystem.stockroll.repository.EstoqueRepository;
import br.com.usystem.stockroll.repository.LocalRepository;
import br.com.usystem.stockroll.repository.LoteRepository;
import br.com.usystem.stockroll.repository.MotivoRepository;
import br.com.usystem.stockroll.repository.MovimentacaoRepository;
import br.com.usystem.stockroll.repository.ProdutoRepository;
import br.com.usystem.stockroll.repository.UsuarioRepository;
import jakarta.websocket.server.PathParam;


@Controller
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    
    private final MovimentacaoRepository movimentacaoRepository;    
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final MotivoRepository motivoRepository;
    private final LocalRepository localRepository;
    private final LoteRepository loteRepository;
    private final EstoqueRepository estoqueRepository;


    @Autowired
    public MovimentacaoController(
                    MovimentacaoRepository movimentacaoRepository,
                    UsuarioRepository usuarioRepository, 
                    ProdutoRepository produtoRepository,
                    MotivoRepository motivoRepository,
                    LocalRepository localRepository,
                    LoteRepository loteRepository,
                    EstoqueRepository estoqueRepository) {

        this.movimentacaoRepository = movimentacaoRepository;                         
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.motivoRepository = motivoRepository;
        this.localRepository = localRepository;
        this.loteRepository = loteRepository;
        this.estoqueRepository = estoqueRepository;
    }

    
    



    @ModelAttribute("locais")
    public List<Local> getLocal() {
        return localRepository.findAll();
    }



    // @GetMapping()
    // public ModelAndView listar() {
    //     var modelAndView = new ModelAndView("movimentacao/listar");

    //     List<Movimentacao> movimentacao = movimentacaoRepository.findAll();
    //     Collections.reverse(movimentacao);
    //     modelAndView.addObject("movimentacao", movimentacao);
        
    //     return modelAndView;
    // }


    /*
     * Recebe uma variável 'local' enviada como 
     * parâmetro para filtrar o local do Estoque
     * 
    */

    @GetMapping
    public ModelAndView listar(@RequestParam(required = false) Integer local) {
        var modelAndView = new ModelAndView("movimentacao/listar");

        List<Movimentacao> movimentacao;

        if(local != null) {
            movimentacao = movimentacaoRepository.findByLocalId(local);
        } else {
            movimentacao = movimentacaoRepository.findAll();
        }
        
        Collections.reverse(movimentacao);
        modelAndView.addObject("movimentacao", movimentacao);
        
        return modelAndView;
    }




    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("movimentacao/detalhar");
            modelAndView.addObject("movimentacao", movimentacaoRepository.getReferenceById(id));        
        
        return modelAndView;
    }

    


    /*
     * 
     * 
     * 
     * ACTIONS PARA OS QUIOSQUES
     * 
     * Por curiosidade descobri que essas actions 
     * não estão mais sendo usadas.
     * Por causa que 'lote/cadastrar' faz o mesmo que elas.
     * 
     * 
    */


    // @GetMapping("/cadastrar/entrada")
    // public ModelAndView cadastrarEntrada() {
    //     var modelAndView = new ModelAndView("/movimentacao/formulario-entrada");

    //         Movimentacao movimentacao = new Movimentacao();                    
    //                 movimentacao.setTipoMovimentacao("Entrada");
    //                 // movimentacao.setMotivo(new Motivo(1, "-"));

    //         modelAndView.addObject("movimentacao", movimentacao);
    //         modelAndView.addObject("usuarios", usuarioRepository.findAll());
    //         var locais = localRepository.findAll(Sort.by("id"));
    //             locais.remove(0);               //  O CD Principal não é exibido para o Usuário.
    //         modelAndView.addObject("locais", locais);                       
    //         modelAndView.addObject("lotes", loteRepository.findAll());
    //         // modelAndView.addObject("produtos", produtoRepository.findAll());     // O lote já possui o nome do produto.

    //     return modelAndView;
    // }



    // @PostMapping("/cadastrar/entrada")
    // public String cadastrarEntrada(Movimentacao movimentacao, Principal principal) {

    //     Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElseThrow();
    //     Lote lote = movimentacao.getLote();     // Provavelmente dê problema.

    //     movimentacao.setUsuario(usuario);
    //     movimentacao.setDataMovimentacao(LocalDateTime.now());
    //     movimentacao.setTipoMovimentacao("Entrada");
    //     movimentacao.setMotivo(new Motivo(1, "-"));
    //     movimentacao.setValorUnitario(lote.getValorUnitario());
        
    //     // movimentacao.setProduto(movimentacao.getLote().getProduto());  // captura o produto com base no Lote excolhido.

    //     /* Atualizar a quantidade de Produto no Estoque */
    //     // Long produtoId = movimentacao.getProduto().getId();
    //     // Produto produto = produtoRepository.getReferenceById(produtoId);

    //     //     produto.setQtdAtualEstoque(produto.getQtdAtualEstoque() + movimentacao.getQuantidade());
    //     // movimentacao.setPreco(produto.getValorUnitario());

    //     // System.out.printf("\n\n --------\n\n %s \n\n-------- \n\n", movimentacao);
    //     // produtoRepository.save(produto);
    //     movimentacaoRepository.save(movimentacao);
        
    //     return "redirect:/movimentacao";
    // }


    /*
        Movimentacao(
            id=null, 
            local=Local(id=1, nome=Principal), 
            usuario=Usuario(id=4, nome=admin, email=admin@gmail.com, senha=$2a$10$QkPjr9.Jj8KPL6cTF2PzA.nsBKfqiCC1PCGtC/k9pZPavjjY9zUTq, perfil=GESTOR, cadastro=2024-04-10T10:28:12), 
            lote=Lote(id=2, produto=Produto(id=2, codigoBarra=3045140105502, nome=Chocolate Milka), nome=Chocol-Jan-2025, vencimento=2025-01-01), 
            motivo=Motivo(id=1, nome=-), 
            produto=Produto(id=2, codigoBarra=3045140105502, nome=Chocolate Milka), 
            dataMovimentacao=2024-05-09, 
            quantidade=10, 
            valorUnitario=8.00, 
            tipoMovimentacao=Entrada)
    */


/*
 * 
 * 
 * ACTION CHAMADA PELO BOTÃO 'TRANSFERIR' DA VIEW LOTES
 * 
 * Essa action recebe o 'Id' do Lote com o produto que será transferido.
 * Essas duas actions são cópias descaradas de 'cadastrarEntrada'
 * de Quiosques. (código acima)
 * 
*/


    @GetMapping("/cadastrar/entrada/{id}")
    public ModelAndView cadastrarEntradaViaTransferencia(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("/movimentacao/formulario-entrada");

            Movimentacao movimentacao = new Movimentacao();                    
                         movimentacao.setTipoMovimentacao("Entrada");  // (remover) trava como uma Entrada
                      // movimentacao.setMotivo(new Motivo(1, "-"));

            modelAndView.addObject("movimentacao", movimentacao);                       // campos necessários para realizar uma movimentação.
            modelAndView.addObject("usuarios", usuarioRepository.findAll());            // (remover) este objeto, pois o Security que devolve o nome.
            var locais = localRepository.findAll(Sort.by("id"));                        // recupera sempre ordenado pelo 'id'
                locais.remove(0);                                                 // o CD-Principal não é exibido para o Usuário.
            modelAndView.addObject("locais", locais);                       
            modelAndView.addObject("lotes", loteRepository.getReferenceById(id));       // recupera o lote que o usuário enviou no parâmetro.
            // modelAndView.addObject("produtos", produtoRepository.findAll());         // O lote já possui o nome do produto.

        return modelAndView;
    }



    @PostMapping("/cadastrar/entrada/{id}")
    public String cadastrarEntradaViaTransferencia(Movimentacao movimentacao, Principal principal, @PathVariable Integer id) { // fui obrigado a incluir o 'id', o VSCode não estava aceitando.

        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElseThrow(); // retorna o usuário logado no sistema.
        
        movimentacao.setId(null);                                            // Cria sempre um novo registro e não permite atualização do 'id'.
        movimentacao.setUsuario(usuario);                                       // usuário que fez a movimentação.
        movimentacao.setDataMovimentacao(LocalDateTime.now());                  // data que foi feita a movimentação.
        movimentacao.setTipoMovimentacao("Entrada");           // trava como uma Entrada.
        movimentacao.setMotivo(new Motivo(1, "-"));                     // trava como '-' porque não é uma 'devolução' ou 'descarte'
        
        Lote lote = movimentacao.getLote();                                     // Captura informações do Lote desta Movimentação.
        movimentacao.setValorUnitario(lote.getValorUnitario());                 // (remover) retorna o preço desse produto e copia para preço na Movimentação. 
        
        // movimentacao.setQuantidade();                                        // foi informado pelo usuário.
        // movimentacao.setValorUnitario();                                     // foi informado pelo usuário.

        // movimentacao.setProduto(movimentacao.getLote().getProduto());        // (remover) captura o produto com base no Lote excolhido.

        /* Atualizar a quantidade de Produto no Estoque */
        // Long produtoId = movimentacao.getProduto().getId();
        // Produto produto = produtoRepository.getReferenceById(produtoId);

        //     produto.setQtdAtualEstoque(produto.getQtdAtualEstoque() + movimentacao.getQuantidade());
        // movimentacao.setPreco(produto.getValorUnitario());

        // System.out.printf("\n\n --------\n\n %s \n\n-------- \n\n", movimentacao);
        // produtoRepository.save(produto);
       

        /* Diminui a quantidade de produtos na tabela 'Lote' */

        Integer diferenca = lote.getQuantidade() - movimentacao.getQuantidade();  // subtrai da quantidade do 'Lote' o total informado pelo usuário nessa 'transferência'.
        lote.setQuantidade(diferenca);                                            // define o novo total de produtos no 'Lote'.

        
        /* ------------------------------------------------------------------------ */


        /* Diminui a quantidade de produtos no Estoque: 'CD - Principal' */
        
       EstoqueId estoqueId2 = new EstoqueId();                                  // inicializa um novo Id composto por (id_local, id_lote)
                 estoqueId2.setLocal(new Local(1, "CD - Principal"));   // acessa somente o CD-Principal
                 estoqueId2.setLote(lote);                                      // que tenha esse lote
       
       Estoque estoqueCdPrincipal = estoqueRepository.getReferenceById(estoqueId2); // recupera os dados desse 'lote' no 'Cd-Principal'
       Integer qtdCdPrincipal = estoqueCdPrincipal.getQuantidade();                 // recupera a quantidade em estoque.
       Integer novaQtdCdPrincipal = qtdCdPrincipal - movimentacao.getQuantidade();  // subtrai a quantidade do Estoque. 

               estoqueCdPrincipal.setQuantidade(novaQtdCdPrincipal);            // atualiza o Estoque com a nova quantidade.


        /* ------------------------------------------------------------------------ */


        /* Adiciona uma nova quantidade de produtos na tabela Estoque: 'Quiosque' */

        EstoqueId estoqueId = new EstoqueId();                                   // inicializa o Id composto de duas chaves estrangeiras (id_local, id_lote)
                  estoqueId.setLocal(movimentacao.getLocal());                   // define o local da movimentação atual.
                  estoqueId.setLote(lote);                                       // define o lote  da movimentação atual.

        

        if(!estoqueRepository.existsById(estoqueId)) {

            /* Cadastra um novo campo na tabela Estoque com a quantidade do Quiosque Atual */

            Estoque estoque = new Estoque();
                    estoque.setId(estoqueId);                                       // recebe a chave estrangeira (id_local, id_lote)
                    estoque.setQuantidade(movimentacao.getQuantidade());   // <---- meio estranho     // define a quantidade para o Quiosque. 
            estoqueRepository.save(estoque);

        } else {
        
            /* Atualiza a quantidade do Quiosque Atual caso ele já exista. */
                
            Estoque estoqueQuiosque = estoqueRepository.getReferenceById(estoqueId); // recupera os dados deste 'lote' no 'Quiosque' escolhido
            Integer qtdQuiosque = estoqueQuiosque.getQuantidade();                   // recupera a quantidade em estoque.
            Integer novaQtdQuiosque = qtdQuiosque + movimentacao.getQuantidade();    // soma (+) a quantidade do Estoque. 

                    estoqueQuiosque.setQuantidade(novaQtdQuiosque);     // <---- meio estranho     // atualiza o Quiosque com a nova quantidade.
                    //estoqueRepository.save(estoqueQuiosque); <---- provavelmente esteja faltando o save.
        }

        /* ------------------------------------------------------------------------ */

        

                movimentacaoRepository.save(movimentacao);
        
        return "redirect:/lote";
    }


    /*
        
        Estoque (
             id = EstoqueId(local=Local(id=1, nome=CD - Principal), 
             lote = Lote(id=4, produto=Produto(id=1, codigoBarra=0000000000001, nome=Leite Jussara), vencimento=2024-06-10, quantidade=50, valorUnitario=1.00)), 
             quantidade=100
             )

    */

    /*
     
        Movimentacao (
            id=1,      <---- ONDE ESTÁ O ERRO!!!! (por isso que sempre atualizava ao invés de cadastrar novo)
            local=Local(id=2, nome=Tatuapé), 
            usuario=Usuario(id=4, nome=admin, email=admin@gmail.com, senha=$2a$10$QkPjr9.Jj8KPL6cTF2PzA.nsBKfqiCC1PCGtC/k9pZPavjjY9zUTq, 
            perfil=GESTOR, 
            cadastro=2024-04-10T10:28:12), 
            lote=Lote(
                        id=1, 
                        produto=Produto(id=1, codigoBarra=0000000000001, nome=Leite Jussara), 
                        vencimento=2024-07-20, 
                        quantidade=100, 
                        valorUnitario=1.00
                      ), 
            motivo=Motivo(id=1, nome=-), 
            dataMovimentacao=2024-05-17T07:13:46.731512500, 
            quantidade=50, 
            valorUnitario=1.00, 
            tipoMovimentacao=Entrada
          )

    */






    @GetMapping("/cadastrar/saida")
    public ModelAndView cadastrarSaida() {
        var modelAndView = new ModelAndView("/movimentacao/formulario-saida");

        Movimentacao movimentacao = new Movimentacao();
                movimentacao.setDataMovimentacao(LocalDateTime.now());
                movimentacao.setTipoMovimentacao("Saida");
                

            modelAndView.addObject("movimentacao", movimentacao);
            modelAndView.addObject("usuarios", usuarioRepository.findAll());
            // modelAndView.addObject("lotes", loteRepository.findAll(Sort.by("produto.nome")));
            modelAndView.addObject("lotes", loteRepository.findAll());
            modelAndView.addObject("motivos", motivoRepository.findAll());
            // modelAndView.addObject("estoques", estoqueRepository.findAll(Sort.by("id.lote.produto.nome")));
            // List<Estoque> estoques = estoqueRepository.findByQuantidadeGreaterThan(0);
            List<Estoque> estoques = estoqueRepository.findAllEstoqueMaiorQueZeroAndOrderByProduto();
            modelAndView.addObject("estoques", estoques);
            var locais = localRepository.findAll(Sort.by("id"));
                locais.remove(0);                           //  O Centro de Distribuição não é listado.
            modelAndView.addObject("locais", locais);                       

        return modelAndView;
    }


   
/*
 
    [
        Estoque(
            id=EstoqueId(
                local=Local(id=1, nome=CD - Principal), 
                lote=Lote(id=1, produto=Produto(id=1, codigoBarra=0000000000001, nome=Leite Jussara), vencimento=2024-10-10, quantidade=0, valorUnitario=1.00)), 
                quantidade=0),             
        Estoque(
            id=EstoqueId(local=Local(id=1, nome=CD - Principal), 
                lote=Lote(id=5, produto=Produto(id=2, codigoBarra=0303939390, nome=Pão de Queijo), vencimento=2024-10-10, quantidade=1000, valorUnitario=2.00)), 
                quantidade=1000),  
        Estoque(
            id=EstoqueId(local=Local(id=3, nome=Aricanduva), 
                lote=Lote(id=3, produto=Produto(id=2, codigoBarra=0303939390, nome=Pão de Queijo), vencimento=2020-02-20, quantidade=0, valorUnitario=4.00)), 
                quantidade=100)
     ]

 */



    @PostMapping("/cadastrar/saida")
    public String cadastrarSaida(Movimentacao movimentacao, Principal principal) {
            
        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElseThrow();

        movimentacao.setUsuario(usuario);
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        movimentacao.setTipoMovimentacao("Saida");

        // Long produtoId = movimentacao.getProduto().getId();
        // Produto produto = produtoRepository.getReferenceById(produtoId);
            // produto.setQtdAtualEstoque(produto.getQtdAtualEstoque() - movimentacao.getQuantidade());
        // movimentacao.setPreco(produto.getValorUnitario());
        
        movimentacaoRepository.save(movimentacao);
        
        return "redirect:/movimentacao";
    }





    /*
     * 
     * 
     * ACTIONS EXCLUSIVAS PARA O CENTRO DE DISTRIBUIÇÃO
     * 
     * 
    */


    @GetMapping("/cadastrar/entrada-cd")
    public ModelAndView cadastrarEntradaCentroDistribuicao() {
        var modelAndView = new ModelAndView("/movimentacao/formulario-entrada-cd");

            Movimentacao movimentacao = new Movimentacao();                    
                    movimentacao.setTipoMovimentacao("Entrada");

            modelAndView.addObject("movimentacao", movimentacao);
            modelAndView.addObject("usuarios", usuarioRepository.findAll());
            modelAndView.addObject("locais", localRepository.findAll());
            modelAndView.addObject("lotes", loteRepository.findAll(Sort.by("produto.nome")));

        return modelAndView;
    }



    @PostMapping("/cadastrar/entrada-cd")
    public String cadastrarEntradaCentroDistribuicao(Movimentacao movimentacao, Principal principal) {

        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElseThrow();

        movimentacao.setUsuario(usuario);
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        movimentacao.setTipoMovimentacao("Entrada");
        movimentacao.setMotivo(new Motivo(1, "-"));        
        movimentacao.setLocal(new Local(1, "CD.Principal"));
        
        movimentacaoRepository.save(movimentacao);
        
        return "redirect:/movimentacao";
    }


}
