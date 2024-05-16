package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.repositories.EstoqueRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/estoque")
public class EstoqueController {
  
  @Autowired
  private EstoqueRepository estoqueRepository;

  @GetMapping
  public ModelAndView listar() {
      var modelAndView = new ModelAndView("estoque/listar");
          modelAndView.addObject("estoques", estoqueRepository.findAll());
    
    return modelAndView;
  }
  

}
