package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.models.Lote;
import br.com.usystem.stockroll.repositories.LoteRepository;
import br.com.usystem.stockroll.repositories.ProdutoRepository;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/lote")
public class LoteController {
    
    
    @Autowired
    LoteRepository loteRepository;

    @Autowired
    ProdutoRepository produtoRepository;


    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("lote/listar");
        modelAndView.addObject("lotes", loteRepository.findAll());

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

    @PostMapping("cadastrar")
    public String cadastrar(Lote lote) {

        loteRepository.save(lote);
        return "redirect:/lote";
    }

    
    
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
