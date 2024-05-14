package br.com.usystem.stockroll.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.models.Movimentacao;
import br.com.usystem.stockroll.models.Local;
import br.com.usystem.stockroll.models.MotivoSaida;
import br.com.usystem.stockroll.models.Produto;
import br.com.usystem.stockroll.models.Usuario;
import br.com.usystem.stockroll.repositories.MovimentacaoRepository;
import br.com.usystem.stockroll.repositories.LocalRepository;
import br.com.usystem.stockroll.repositories.LoteRepository;
import br.com.usystem.stockroll.repositories.MotivoSaidaRepository;
import br.com.usystem.stockroll.repositories.ProdutoRepository;
import br.com.usystem.stockroll.repositories.UsuarioRepository;

@Controller
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    

    private final MovimentacaoRepository movimentacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final MotivoSaidaRepository motivoSaidaRepository;
    private final LocalRepository localRepository;
    private final LoteRepository loteRepository;


    @Autowired
    public MovimentacaoController(MovimentacaoRepository movimentacaoRepository, 
                    UsuarioRepository usuarioRepository, 
                    ProdutoRepository produtoRepository,
                    MotivoSaidaRepository motivoSaidaRepository,
                    LocalRepository localRepository,
                    LoteRepository loteRepository) {

        this.movimentacaoRepository = movimentacaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.motivoSaidaRepository = motivoSaidaRepository;
        this.localRepository = localRepository;
        this.loteRepository = loteRepository;
    }



    @GetMapping()
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("movimentacao/listar");

        List<Movimentacao> movimentacao = movimentacaoRepository.findAll();
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
     * CONTROLLER PARA OS QUIOSQUES
    */


    @GetMapping("/cadastrar/entrada")
    public ModelAndView cadastrarEntrada() {
        var modelAndView = new ModelAndView("/movimentacao/formulario-entrada");

            Movimentacao movimentacao = new Movimentacao();                    
                    movimentacao.setTipoMovimentacao("Entrada");
                    // movimentacao.setMotivoSaida(new MotivoSaida(1, "-"));

            modelAndView.addObject("movimentacao", movimentacao);
            modelAndView.addObject("usuarios", usuarioRepository.findAll());
            modelAndView.addObject("locais", localRepository.findAll());
            modelAndView.addObject("lotes", loteRepository.findAll(Sort.by("produto.nome")));
            // modelAndView.addObject("produtos", produtoRepository.findAll()); // O lote já contém o nome do produto.

        return modelAndView;
    }



    @PostMapping("/cadastrar/entrada")
    public String cadastrarEntrada(Movimentacao movimentacao, Principal principal) {

        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElseThrow();

        movimentacao.setUsuario(usuario);
        movimentacao.setDataMovimentacao(LocalDate.now());
        movimentacao.setTipoMovimentacao("Entrada");
        movimentacao.setMotivoSaida(new MotivoSaida(1, "-"));
        // movimentacao.setProduto(movimentacao.getLote().getProduto());  // captura o produto com base no Lote excolhido.

        /* Atualizar a quantidade de Produto no Estoque */
        // Long produtoId = movimentacao.getProduto().getId();
        // Produto produto = produtoRepository.getReferenceById(produtoId);

        //     produto.setQtdAtualEstoque(produto.getQtdAtualEstoque() + movimentacao.getQuantidade());
        // movimentacao.setPreco(produto.getValorUnitario());

        // System.out.printf("\n\n --------\n\n %s \n\n-------- \n\n", movimentacao);
        // produtoRepository.save(produto);
        movimentacaoRepository.save(movimentacao);
        
        return "redirect:/movimentacao";
    }


    /*
        Movimentacao(
            id=null, 
            local=Local(id=1, nome=Principal), 
            usuario=Usuario(id=4, nome=admin, email=admin@gmail.com, senha=$2a$10$QkPjr9.Jj8KPL6cTF2PzA.nsBKfqiCC1PCGtC/k9pZPavjjY9zUTq, perfil=GESTOR, cadastro=2024-04-10T10:28:12), 
            lote=Lote(id=2, produto=Produto(id=2, codigoBarra=3045140105502, nome=Chocolate Milka), nome=Chocol-Jan-2025, vencimento=2025-01-01), 
            motivoSaida=MotivoSaida(id=1, nome=-), 
            produto=Produto(id=2, codigoBarra=3045140105502, nome=Chocolate Milka), 
            dataMovimentacao=2024-05-09, 
            quantidade=10, 
            valorUnitario=8.00, 
            tipoMovimentacao=Entrada)
    */




    

    @GetMapping("/cadastrar/saida")
    public ModelAndView cadastrarSaida() {
        var modelAndView = new ModelAndView("/movimentacao/formulario-saida");

        Movimentacao movimentacao = new Movimentacao();
                movimentacao.setDataMovimentacao(LocalDate.now());
                movimentacao.setTipoMovimentacao("Saida");
                

            modelAndView.addObject("movimentacao", movimentacao);
            modelAndView.addObject("usuarios", usuarioRepository.findAll());
            modelAndView.addObject("locais", localRepository.findAll());
            modelAndView.addObject("lotes", loteRepository.findAll(Sort.by("produto.nome")));
            modelAndView.addObject("motivos", motivoSaidaRepository.findAll());

        return modelAndView;
    }


   

    @PostMapping("/cadastrar/saida")
    public String cadastrarSaida(Movimentacao movimentacao, Principal principal) {
            
        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElseThrow();

        movimentacao.setUsuario(usuario);
        movimentacao.setDataMovimentacao(LocalDate.now());
        movimentacao.setTipoMovimentacao("Saida");

        // Long produtoId = movimentacao.getProduto().getId();
        // Produto produto = produtoRepository.getReferenceById(produtoId);
            // produto.setQtdAtualEstoque(produto.getQtdAtualEstoque() - movimentacao.getQuantidade());
        // movimentacao.setPreco(produto.getValorUnitario());
        
        movimentacaoRepository.save(movimentacao);
        
        return "redirect:/movimentacao";
    }







    /*
     * CONTROLLER EXCLUSIVA PARA O CENTRO DE DISTRIBUIÇÃO
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
        movimentacao.setDataMovimentacao(LocalDate.now());
        movimentacao.setTipoMovimentacao("Entrada");
        movimentacao.setMotivoSaida(new MotivoSaida(1, "-"));        
        movimentacao.setLocal(new Local(1, "CD.Principal"));
        
        movimentacaoRepository.save(movimentacao);
        
        return "redirect:/movimentacao";
    }


}
