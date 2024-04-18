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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.models.Estoque;
import br.com.usystem.stockroll.models.MotivoSaida;
import br.com.usystem.stockroll.models.Produto;
import br.com.usystem.stockroll.models.Usuario;
import br.com.usystem.stockroll.repositories.EstoqueRepository;
import br.com.usystem.stockroll.repositories.MotivoSaidaRepository;
import br.com.usystem.stockroll.repositories.ProdutoRepository;
import br.com.usystem.stockroll.repositories.UsuarioRepository;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {
    

    private final EstoqueRepository estoqueRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final MotivoSaidaRepository motivoSaidaRepository;


    @Autowired
    public EstoqueController(EstoqueRepository estoqueRepository, 
                    UsuarioRepository usuarioRepository, 
                    ProdutoRepository produtoRepository,
                    MotivoSaidaRepository motivoSaidaRepository) {

        this.estoqueRepository = estoqueRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.motivoSaidaRepository = motivoSaidaRepository;
    }



    @GetMapping()
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("estoque/listar");

        List<Estoque> movimentacao = estoqueRepository.findAll();
        Collections.reverse(movimentacao);
        modelAndView.addObject("movimentacao", movimentacao);
        
        return modelAndView;
    }



    @GetMapping("/cadastrar/entrada")
    public ModelAndView cadastrarEntrada() {
        var modelAndView = new ModelAndView("/estoque/formulario-entrada");

            Estoque estoque = new Estoque();
                    estoque.setDataMovimentacao(LocalDate.now());
                    estoque.setTipoMovimentacao("Entrada");
                    estoque.setQuantidade(1);
                    // estoque.setMotivoSaida(new MotivoSaida(1, "-"));
                    
                    // var motivo = new MotivoSaida();
                    //     motivo.setDescricao("-");
                    // estoque.setMotivoSaida(motivo);

            modelAndView.addObject("estoque", estoque);
            modelAndView.addObject("usuarios", usuarioRepository.findAll());
            modelAndView.addObject("produtos", produtoRepository.findAll());


        return modelAndView;
    }



    @PostMapping("/cadastrar/entrada")
    public String cadastrarEntrada(Estoque estoque, Principal principal) {

        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElseThrow();

        estoque.setUsuario(usuario);
        estoque.setTipoMovimentacao("Entrada");
        estoque.setMotivoSaida(new MotivoSaida(1, "-"));

        Long produtoId = estoque.getProduto().getId();
        Produto produto = produtoRepository.getReferenceById(produtoId);

            produto.setQtdAtualEstoque(produto.getQtdAtualEstoque() + estoque.getQuantidade());
        estoque.setPreco(produto.getValorUnitario());


        //produtoRepository.save(produto);
        estoqueRepository.save(estoque);
        
        return "redirect:/estoque";
    }



    @GetMapping("/cadastrar/saida")
    public ModelAndView cadastrarSaida() {
        var modelAndView = new ModelAndView("/estoque/formulario-saida");

        Estoque estoque = new Estoque();
                estoque.setQuantidade(1);
                estoque.setDataMovimentacao(LocalDate.now());
                estoque.setTipoMovimentacao("Saida");
                

            modelAndView.addObject("estoque", estoque);
            modelAndView.addObject("usuarios", usuarioRepository.findAll());
            modelAndView.addObject("produtos", produtoRepository.findAll());
            modelAndView.addObject("motivos", motivoSaidaRepository.findAll());

        return modelAndView;
    }


   

    @PostMapping("/cadastrar/saida")
    public String cadastrarSaida(Estoque estoque, Principal principal) {
            
        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElseThrow();

        estoque.setUsuario(usuario);
        estoque.setTipoMovimentacao("Saida");
        Long produtoId = estoque.getProduto().getId();
        Produto produto = produtoRepository.getReferenceById(produtoId);
            
            produto.setQtdAtualEstoque(produto.getQtdAtualEstoque() - estoque.getQuantidade());

        estoque.setPreco(produto.getValorUnitario());

        estoqueRepository.save(estoque);
        
        return "redirect:/estoque";
    }

}
