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
          modelAndView.addObject("estoques", estoqueRepository.findByQuantidadeGreaterThan(0)); // lista pela quantidade maior que '0'
          
    return modelAndView;
  }
  

  /*
    EstoqueId
      (
        local=Local(id=1, nome=CD - Principal), 
        lote=Lote(id=1, produto=Produto(id=1, codigoBarra=0000000000001, nome=Leite Jussara), vencimento=2024-06-10, quantidade=100, valorUnitario=1.00)
      )

  */

}
