package br.com.usystem.stockroll.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.repository.LoteRepository;
import br.com.usystem.stockroll.service.DashboardService;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Controller
@RequestMapping("/dashboard") 
public class DashboardController {
  
    private LoteRepository loteRepository;
    private DashboardService dashboardService;
    
   
    @GetMapping
    public ModelAndView dashboard() {
		var modelAndView = new ModelAndView("/dashboard");

		Integer totalEstoque = loteRepository.selectSumQtdProdutos();
    BigDecimal valorEstoque = loteRepository.selectSumValorUnitario();

        modelAndView.addObject("totalEstoque", totalEstoque);
        modelAndView.addObject("valorEstoque", valorEstoque);
        modelAndView.addObject("quiosques", dashboardService.resumoDosQuiosques());

        /* pesquisa o nível do estoque e emite um alerta */
        // modelAndView.addObject("alertas", estoqueService.verficaStatusDoEstoque());
        // modelAndView.addObject("alertas", "Leite em Pó - 1Kg");
        
        modelAndView.addObject("alertas", dashboardService.alertaMinimoEmEstoque());
        System.out.println(dashboardService.alertaMinimoEmEstoque()); 
        return modelAndView;
    }

}
