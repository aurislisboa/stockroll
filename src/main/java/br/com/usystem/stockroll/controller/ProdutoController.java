package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.models.Produto;
import br.com.usystem.stockroll.repositories.ProdutoRepository;

import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;



    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("/produto/listar.html");

        List<Produto> produtos = produtoRepository.findAll();
        modelAndView.addObject("produtos", produtos);
        return modelAndView;
    }


//    @GetMapping
//    public List<Produto> listar() {
//        return repository.findAll();
//    }


    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/produto/detalhar.html");

        Produto produto = produtoRepository.getReferenceById(id);
        modelAndView.addObject("produto", produto);
        return modelAndView;
    }



    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("produto/formulario");
        modelAndView.addObject("produto", new Produto());

        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Produto produto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/produto");
        //System.out.println("Dados do Cadastro:" + produto.toString());
        produtoRepository.save(produto);

        return modelAndView;
    }



    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("produto/formulario");

        Produto produto = produtoRepository.getReferenceById(id);
        modelAndView.addObject("produto", produto);
        return modelAndView;
    }

    @PostMapping("/{id}/editar")
    public ModelAndView editar(Produto produto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/produto");
        //System.out.println(produto);
        produtoRepository.save(produto);
        return modelAndView;
    }



    @GetMapping("/{id}/excluir")
    public ModelAndView exluir(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/produto");
        produtoRepository.deleteById(id);

        return modelAndView;
    }

}
