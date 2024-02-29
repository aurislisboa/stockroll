package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.models.Estoque;
import br.com.usystem.stockroll.repositories.EstoqueRepository;
import br.com.usystem.stockroll.repositories.ProdutoRepository;
import br.com.usystem.stockroll.repositories.UsuarioRepository;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {
    

    private final EstoqueRepository estoqueRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public EstoqueController(EstoqueRepository estoqueRepository, UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }


    @GetMapping()
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("estoque/listar");

        var movimentacaoEstoque = estoqueRepository.findAll();
        // System.out.println("--------------\n\n" + movimentacaoEstoque);
        modelAndView.addObject("movimentacao", movimentacaoEstoque);

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("/estoque/formulario");

            modelAndView.addObject("estoque", new Estoque());
            modelAndView.addObject("usuarios", usuarioRepository.findAll());
            modelAndView.addObject("produtos", produtoRepository.findAll());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Estoque estoque) {
        System.out.println("----------\n\n" + estoque);

        estoqueRepository.save(estoque);
        
        return "redirect:/estoque";
    }

}
