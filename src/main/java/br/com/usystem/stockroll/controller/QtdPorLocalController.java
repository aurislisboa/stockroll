package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.repositories.QtdPorLocalRepository;

@Controller
@RequestMapping("/quantidade")
public class QtdPorLocalController {
  

  @Autowired
  private QtdPorLocalRepository qtdPorLocalRepository;



  @GetMapping
  public ModelAndView listar() {
    var modelAndView = new ModelAndView("quantidade/listar");
        modelAndView.addObject("quantidades", qtdPorLocalRepository.findAll());
        
    return modelAndView;
  }



}
