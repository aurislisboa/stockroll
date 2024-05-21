package br.com.usystem.stockroll.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.models.Estoque;
import br.com.usystem.stockroll.models.EstoqueId;
import br.com.usystem.stockroll.models.Local;
import br.com.usystem.stockroll.models.Lote;
import br.com.usystem.stockroll.models.Motivo;
import br.com.usystem.stockroll.models.Movimentacao;
import br.com.usystem.stockroll.models.Usuario;
import br.com.usystem.stockroll.repositories.EstoqueRepository;
import br.com.usystem.stockroll.repositories.LoteRepository;
import br.com.usystem.stockroll.repositories.MovimentacaoRepository;
import br.com.usystem.stockroll.repositories.ProdutoRepository;
import br.com.usystem.stockroll.repositories.UsuarioRepository;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/lote")
public class LoteController {
    
    
    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;


    // deu erro, porque a movimentação guardou a informação da transação anterior.
    @Autowired 
    private EstoqueRepository estoqueRepository;     



    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("lote/listar");
        // modelAndView.addObject("lotes", loteRepository.findAll());
        modelAndView.addObject("lotes", loteRepository.findByQuantidadeGreaterThan(0));

        return modelAndView;
    }



    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("lote/detalhar");

        modelAndView.addObject("lote", loteRepository.getReferenceById(id));
        return modelAndView;
    }



    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("lote/formulario");

        modelAndView.addObject("lote", new Lote());
        modelAndView.addObject("produtos", produtoRepository.findAll(Sort.by("nome")));
        return modelAndView;
    }

    /*
     * 
     *  Action Original para Cadastrar um Produto no Lote
     * 
     */


    // @PostMapping("cadastrar")
    // public String cadastrar(Lote lote) {

    //     loteRepository.save(lote);
    //     return "redirect:/lote";
    // }





    /*
     * 
     *  Action responsável por cadastrar um produto no na tabela Lote,
     *  criar um registro na tabela Movimentação 
     *  e cadastrar um novo lote na tabela Estoque.
     * 
     */


    @PostMapping("cadastrar")
    public String cadastrar(Lote lote, Principal principal) {   // Recebe os dados do lote e o Usuário ativo

            /*
            * Primeiro é salvo o Lote
            */

            loteRepository.save(lote);          // cadastra um novo lote na tabela Lote.
            
            /*
            *  Depois é definido os campos pora inserir uma Movimentação de Entrada no CD - Principal.
            *  O código abaixo é uma cópia descarada da Action 'cadastrar' de MovimentacaoController
            *  'movimentacao/cadastrar/entrada'
            */

            Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElseThrow();
            Movimentacao movimentacao = new Movimentacao();
                    movimentacao.setUsuario(usuario);
                    movimentacao.setDataMovimentacao(LocalDateTime.now());
                    movimentacao.setTipoMovimentacao("Entrada");
                    movimentacao.setMotivo(new Motivo(1, "-"));        
                    movimentacao.setLocal(new Local(1, "CD.Principal"));
                    // ------------------------------------- 
                    movimentacao.setLote(lote);                                 // lote que acabou de ser cadastrado.
                    movimentacao.setQuantidade(lote.getQuantidade());           // quantidade de produtos do lote que acabou de ser cadastrado.
                    movimentacao.setValorUnitario(lote.getValorUnitario());     

            movimentacaoRepository.save(movimentacao);

            /*  Cadastrar Lote na tabela Estoque  */
            
            EstoqueId estoqueId = new EstoqueId();
                    estoqueId.setLocal(new Local(1, "CD - Pricipal"));
                    estoqueId.setLote(lote);

            Estoque estoque = new Estoque();
                    estoque.setId(estoqueId);
                    estoque.setQuantidade(lote.getQuantidade());

            // Estoque estoque = new Estoque();
                    // estoque.setLocal(new Local(1, "CD - Pricipal"));
                    // estoque.setLote(lote);
                    // estoque.setQuantidade(lote.getQuantidade());


            estoqueRepository.save(estoque);
        
        return "redirect:/lote";
    }

    /*
    * 
    *  Exemplo de como foram capturado os dados.
    * 
    */

    /*
     
    Lote(id=7, produto=Produto(id=2, codigoBarra=0000000000002, nome=Leite Pirancajuba), vencimento=2025-05-05, quantidade=50, valorUnitario=5.05)
    
    Movimentacao(
        id=null, 
        local=Local(id=1, nome=CD.Principal), 
        usuario=Usuario(id=4, nome=admin, email=admin@gmail.com, senha=$2a$10$QkPjr9.Jj8KPL6cTF2PzA, perfil=GESTOR, cadastro=2024-04-10T10:28:12), 
        lote=Lote(id=7, produto=Produto(id=2, codigoBarra=0000000000002, nome=Leite Pirancajuba), vencimento=2025-05-05, quantidade=50, valorUnitario=5.05),          
        motivo=Motivo(id=1, nome=-), 
        dataMovimentacao=2024-05-16, 
        quantidade=50, 
        valorUnitario=5.05, 
        tipoMovimentacao=Entrada)

     */
    
    
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("lote/formulario");
        
        modelAndView.addObject("lote", loteRepository.getReferenceById(id));
        modelAndView.addObject("produtos", produtoRepository.findAll(Sort.by("nome")));
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String editar(Lote lote) {

        loteRepository.save(lote);
        return "redirect:/lote";
    }




    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {

        loteRepository.deleteById(id);
        return "redirect:/lote";
    }



}
