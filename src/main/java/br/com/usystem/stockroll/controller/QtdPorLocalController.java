package br.com.usystem.stockroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.model.QtdPorLocal;
import br.com.usystem.stockroll.repository.QtdPorLocalRepository;
import br.com.usystem.stockroll.service.QtdPorLocalService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/quantidade")
@AllArgsConstructor
public class QtdPorLocalController {
  

  private QtdPorLocalRepository qtdPorLocalRepository;
  private QtdPorLocalService qtdPorLocalService;



  @GetMapping
  public ModelAndView listar() {
    var modelAndView = new ModelAndView("quantidade/listar");
    modelAndView.addObject("quantidades", qtdPorLocalRepository.findAll());  

    return modelAndView;
  }


  @GetMapping("/{localId}/{produtoId}")
  public ModelAndView editar(@PathVariable Integer localId, @PathVariable Integer produtoId) {
    var modelAndView = new ModelAndView("/quantidade/formulario");
        modelAndView.addObject("quantidade", qtdPorLocalService.buscaPorLocalEProduto(localId, produtoId));

    return modelAndView;
  }



@PostMapping("/{localId}/{produtoId}")
public String editar(QtdPorLocal qtdPorLocal) {

        //  QtdPorLocalId qtdPorLocalId = new QtdPorLocalId();
        //               qtdPorLocalId.setLocal(qtdPorLocal.getId().getLocal());       
        //               qtdPorLocalId.setProduto(qtdPorLocal.getId().getProduto());
      
         //qtdPorLocal.setId(qtdPorLocalId);                      // a JPA reclamou que não havia uma Id.

        //  System.out.println("Objeto RECEBIDO VIA POST:");
        //  System.out.println("\n\n------- \n\n"+ qtdPorLocal +"\n\n--------------\n\n");

      qtdPorLocalRepository.save(qtdPorLocal);
  return "redirect:/quantidade";
}















}




