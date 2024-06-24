package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.repository.EstoqueRepository;


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


  /* ESSES MÉTODOS NÃO FORAM IMPLEMENTADOS */
  // ----- Porque ao editar um Lote ele já atualiza a quantidade no Estoque ---- //

  // @GetMapping("/{localId}/{loteId}")
  // public ModelAndView editar(@PathVariable Integer localId, @PathVariable Integer loteId) {
  //   var modelAndView = new ModelAndView("/estoque/formulario");
  //       modelAndView.addObject("estoque", estoqueRepository.findByLocalAndLoteId(localId, loteId));

  //   return modelAndView;
  // }


  // @PostMapping("/{localId}/{produtoId}")
  // public String editar(Estoque estoque) {
  //   //estoqueRepository.save(estoque);
  // return "redirect:/lote";
  // }


}
