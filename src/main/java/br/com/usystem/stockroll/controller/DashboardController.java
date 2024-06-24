package br.com.usystem.stockroll.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.repository.LoteRepository;
import br.com.usystem.stockroll.service.DashboardService;
import br.com.usystem.stockroll.service.MovimentacaoService;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Controller
@RequestMapping("/dashboard") 
public class DashboardController {
  
    private LoteRepository loteRepository;
    private DashboardService dashboardService;
    private MovimentacaoService movimentacaoService;
    
   
    @GetMapping
    public ModelAndView dashboard() {
        var modelAndView = new ModelAndView("/dashboard");

          modelAndView.addObject("totalEstoque", loteRepository.selectSumQtdProdutos());
          modelAndView.addObject("totalSaidas", movimentacaoService.totalSaidas());
          modelAndView.addObject("valorEstoque", loteRepository.selectSumValorUnitario());
          modelAndView.addObject("quiosques", dashboardService.resumoDosQuiosques());

          /* pesquisa o nível do estoque e emite um alerta */
          // modelAndView.addObject("alertas", estoqueService.verficaStatusDoEstoque());
          // modelAndView.addObject("alertas", "Leite em Pó - 1Kg");
          
          modelAndView.addObject("alertas", dashboardService.alertaMinimoEmEstoque());
          // System.out.println(dashboardService.alertaMinimoEmEstoque()); 

          modelAndView.addObject("pieChartMap", dashboardService.produtosMaiorSaida());
          modelAndView.addObject("donutChartMap", dashboardService.totalSaidasPorQuiosque());

        return modelAndView;
    }

}
