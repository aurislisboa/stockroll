package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.repository.QtdPorLocalRepository;

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


  @GetMapping("/{localId}/{produtoId}")
  public ModelAndView editar(@PathVariable Integer localId, @PathVariable Integer produtoId) {
    var modelAndView = new ModelAndView("/quantidade/formulario");
        modelAndView.addObject("quantidade", qtdPorLocalRepository.findByLocalAndProdutoId(localId, produtoId));

    return modelAndView;
  }




// Exemplo de como os dados são eviados:  
/*

  [
    QtdPorLocal
     (
      local=Local(id=1, nome=CD - Principal), 
      produto=Produto(id=1, codigoBarra=0000000000001, nome=Leite Jussara), 
      minimo=50, 
      ideal=300
     )
  ]

*/



}




