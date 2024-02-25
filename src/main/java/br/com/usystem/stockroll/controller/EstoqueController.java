package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.repositories.EstoqueRepository;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {
    

    private final EstoqueRepository estoqueRepository;

    @Autowired
    public EstoqueController(EstoqueRepository repository) {
        this.estoqueRepository = repository;
    }


    @GetMapping()
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("estoque/listar");

        var movimentacaoEstoque = estoqueRepository.findAll();
        System.out.println("***********************\n\n" + movimentacaoEstoque);
        modelAndView.addObject("movimentacao", movimentacaoEstoque);

        return modelAndView;
    }

}
