package br.com.usystem.stockroll.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.usystem.stockroll.dto.AlertDTO;
import br.com.usystem.stockroll.model.Estoque;
import br.com.usystem.stockroll.model.EstoqueId;
import br.com.usystem.stockroll.model.Local;
import br.com.usystem.stockroll.model.Lote;
import br.com.usystem.stockroll.model.Movimentacao;
import br.com.usystem.stockroll.model.Usuario;
import br.com.usystem.stockroll.repository.EstoqueRepository;
import br.com.usystem.stockroll.repository.LocalRepository;
import br.com.usystem.stockroll.repository.LoteRepository;
import br.com.usystem.stockroll.repository.MotivoRepository;
import br.com.usystem.stockroll.repository.MovimentacaoRepository;
import br.com.usystem.stockroll.repository.UsuarioRepository;
import br.com.usystem.stockroll.service.EstoqueService;
import br.com.usystem.stockroll.service.MovimentacaoService;
import br.com.usystem.stockroll.service.UsuarioService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    
    private MovimentacaoRepository movimentacaoRepository;    
    private UsuarioRepository usuarioRepository;
    private MotivoRepository motivoRepository;
    private LocalRepository localRepository;
    private LoteRepository loteRepository;
    private EstoqueRepository estoqueRepository;

    private MovimentacaoService movimentacaoService;   
    private UsuarioService usuarioService;


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


    /* Recebe uma variável 'local' enviada como parâmetro para filtrar o local do Estoque */

    @GetMapping
    public ModelAndView listar(@RequestParam(required = false) Integer local) {
        var modelAndView = new ModelAndView("movimentacao/listar");

            List<Movimentacao> movimentacao;

            if(local != null) {
                movimentacao = movimentacaoService.buscarLocalPorId(local);    // filtra somente pelo local esclhido.
            } else { 
                movimentacao = movimentacaoService.buscarTodos(); 
            }
        
            modelAndView.addObject("movimentacao", movimentacao);
            modelAndView.addObject("msg", local);

        return modelAndView;
    }




    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("movimentacao/detalhar");
            modelAndView.addObject("movimentacao", movimentacaoService.buscarPorId(id));        
        
        return modelAndView;
    }

    


    /*
     * 
     * 
     * 
     * ACTIONS PARA OS QUIOSQUES
     * 
     * Essas actions não estão mais sendo usadas.
     * Por causa que 'lote/cadastrar' faz o mesmo que elas.
     * 
     * 
    */


/*
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
 */    

/*
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

    //     // **** Atualizar a quantidade de Produto no Estoque **** //
    //     // Long produtoId = movimentacao.getProduto().getId();
    //     // Produto produto = produtoRepository.getReferenceById(produtoId);

    //     //     produto.setQtdAtualEstoque(produto.getQtdAtualEstoque() + movimentacao.getQuantidade());
    //     // movimentacao.setPreco(produto.getValorUnitario());

    //     // System.out.printf("\n\n --------\n\n %s \n\n-------- \n\n", movimentacao);
    //     // produtoRepository.save(produto);
    //     movimentacaoRepository.save(movimentacao);
        
    //     return "redirect:/movimentacao";
    // }
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

    /* Cadastra uma ENTRADA no ESTOQUE via GET. --- ID do LOTE */

    @GetMapping("/cadastrar/entrada/{id}")
    public ModelAndView cadastrarEntradaPorLote(@PathVariable Integer id) {    // id do LOTE. 
        var modelAndView = new ModelAndView("/movimentacao/formulario-entrada");

        Movimentacao movimentacao = new Movimentacao();                    
                        //  movimentacao.setDataMovimentacao(LocalDateTime.now());
                        //  movimentacao.setTipoMovimentacao("Entrada");  // (remover) trava como uma Entrada
                      // movimentacao.setMotivo(new Motivo(1, "-")); 

            modelAndView.addObject("movimentacao", movimentacao);                       // campos necessários para realizar uma movimentação.
            // modelAndView.addObject("usuarios", usuarioRepository.findAll());            // (remover) este objeto, pois o Security que devolve o nome.
            var locais = localRepository.findAll(Sort.by("id"));                        // recupera sempre ordenado pelo 'id'
                locais.remove(0);                                                 // o CD-Principal não é exibido para o Usuário.
            modelAndView.addObject("locais", locais);                       
            // modelAndView.addObject("lotes", loteRepository.getReferenceById(id));       // recupera o lote que o usuário enviou no parâmetro.
            Lote lote = loteRepository.getReferenceById(id);
            EstoqueId estoqueId = new EstoqueId(new Local(1, "CD-Principal"), lote);
            modelAndView.addObject("estoques", estoqueRepository.getReferenceById(estoqueId));       // recupera o lote que o usuário enviou no parâmetro.
            // modelAndView.addObject("produtos", produtoRepository.findAll());         // O lote já possui o nome do produto.
            // modelAndView.addObject("msg", id);                                          // ajuda o JavaScript escolher

        return modelAndView;
    }


    /* Cadastra uma ENTRADA no ESTOQUE via POST. --- ID do LOTE */

    @PostMapping("/cadastrar/entrada/{id}")
    public String cadastrarEntradaPorLote(Movimentacao movimentacao, Principal principal, @PathVariable Integer id, RedirectAttributes attr) { // id do LOTE.  // fui obrigado a incluir o 'id', o VSCode reclamou.

        if (id == null) {
            return "redirect:/lote";                // Criar uma mensagem de erro, ou devolver um page: 404
        }

        boolean isMaior = movimentacaoService.isQtdSuperiorEmEstoque(movimentacao.getLote(), movimentacao.getQuantidade());
        // if(isMaior) throw new IllegalArgumentException("Não foi dessa vez Gerson!");              // a quantidade informada é maior que a quantidade em estoque.
        if(isMaior) {
            AlertDTO alert = new AlertDTO("Quantidade superior ao Estoque. Não foi dessa vez Gerson :)", "error");
            attr.addFlashAttribute("alert", alert);
            return "redirect:/lote";
        }

        movimentacaoService.cadastrarEntradaNoQuiosqueViaTransferencia(movimentacao, principal);

        AlertDTO alert = new AlertDTO("Produto transferido para o quiosque!", "success");
        attr.addFlashAttribute("alert", alert);
        
        return "redirect:/lote";
    }



    /* Cadastra uma Saída do Quiosque via GET. --- ID do Local */

    @GetMapping("/cadastrar/saida/{id}")        
    public ModelAndView cadastrarSaida(@PathVariable(required = false) Integer id) {     // id do LOCAL do Quiosque. 
        var modelAndView = new ModelAndView("/movimentacao/formulario-saida");
         
        if(id == 1 || id == null) {                               // restringe o acesso ao id=1 (estoque principal)
            throw new ResourceAccessException("Página não encontrada!");
        }

        Movimentacao movimentacao = new Movimentacao();
                movimentacao.setDataMovimentacao(LocalDateTime.now());                  
                movimentacao.setTipoMovimentacao("Saida");
                

            modelAndView.addObject("movimentacao", movimentacao);
            modelAndView.addObject("usuarios", usuarioRepository.findAll());                // (remover) o spring security já devolve o usuário logado.
            // modelAndView.addObject("lotes", loteRepository.findAll(Sort.by("produto.nome")));
            modelAndView.addObject("lotes", loteRepository.findAll());                      // (remover) acho que não está sendo mais usado.
            modelAndView.addObject("motivos", motivoRepository.findAll(Sort.by("id")));
            // modelAndView.addObject("estoques", estoqueRepository.findAll(Sort.by("id.lote.produto.nome")));
            // List<Estoque> estoques = estoqueRepository.findByQuantidadeGreaterThan(0);
            // List<Estoque> estoques = estoqueRepository.findAllEstoqueMaiorQueZeroAndOrderByProduto();
            List<Estoque> estoques = estoqueRepository
                  .procuraPeloLocalComQuantidadeMaiorQueZeroEOrdenaPeloNomeDoProduto(id);   // Procura por esse Local específico no Estoque.
            modelAndView.addObject("estoques", estoques);
            
            var locais = localRepository.findAll(Sort.by("id"));                            // para usar esse bloco é preciso habilitar o JavaScript.
                locais.remove(0);                                                     //  O Centro de Distribuição não pode ser listado.
            modelAndView.addObject("locais", locais);   
            
            // modelAndView.addObject("locais", localRepository.getReferenceById(id));      // caso o acesso seja feito por um botão único.
            modelAndView.addObject("msg", id);                                              // usado no <option> para comparar se está 'selected'.

        return modelAndView;
    }





    /* Cadastra uma Saída do Quiosque via POST. --- ID do Local */

    @PostMapping("/cadastrar/saida/{id}")
    public String cadastrarSaida(Movimentacao movimentacao, Principal principal, @PathVariable(required = false) Integer id, RedirectAttributes attr) {   // id do LOCAL do Estoque
            
            if(id == 1) throw new DataIntegrityViolationException("Você não tem acesso a essa página!");  // id do CD-Principal
           
            try {
                movimentacaoService.cadastrarSaidaDoQuiosque(movimentacao, principal);                
            } catch (DataIntegrityViolationException e) {
                AlertDTO alert = new AlertDTO("Quantidade superior ao Estoque. Não foi dessa vez Gerson :)", "error");
                attr.addFlashAttribute("alert", alert);
                return "redirect:/movimentacao";
            }
            

            AlertDTO alert = new AlertDTO("Saída registrada com sucesso!", "success");
            attr.addFlashAttribute("alert", alert);
        
        return "redirect:/movimentacao/cadastrar/saida/"+id;              // retorna para a tela o id do local do quiosque.
    }




    /*
     * 
     * 
     * ACTIONS EXCLUSIVAS PARA O CENTRO DE DISTRIBUIÇÃO
     * 
     * A Action LOTE já faz tudo isso!
     * Provavelmente não serão mais usada essas Actions.
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
            modelAndView.addObject("motivos", motivoRepository.findAll());

        return modelAndView;
    }



    /* Não está salvando no banco. Essa action não será mais usada */

    @PostMapping("/cadastrar/entrada-cd")
    public String cadastrarEntradaCentroDistribuicao(Movimentacao movimentacao, Principal principal) {

        Usuario usuario = usuarioService.getUsuarioLogadoNoSistema(principal);

        movimentacao.setUsuario(usuario);
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        movimentacao.setTipoMovimentacao("Entrada");
        // movimentacao.setMotivo(new Motivo(1, "-"));        
        movimentacao.setLocal(new Local(1, "CD.Principal"));
        
        movimentacaoRepository.save(movimentacao);
        
        return "redirect:/movimentacao";
    }


}
