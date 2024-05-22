package br.com.usystem.stockroll.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.repository.LoteRepository;
import br.com.usystem.stockroll.repository.ProdutoRepository;

@Controller
@RequestMapping
public class HomeController {


	  @Autowired
    LoteRepository loteRepository;
    
    //@RequestMapping(path = "/", method = RequestMethod.GET)
    @GetMapping
    public ModelAndView home() {
		var modelAndView = new ModelAndView("/home");

		Integer totalEstoque = loteRepository.selectSumQtdProdutos();
    BigDecimal valorEstoque = loteRepository.selectSumValorUnitario();

        modelAndView.addObject("totalEstoque", totalEstoque);
        modelAndView.addObject("valorEstoque", valorEstoque);
        return modelAndView;
    }


    @GetMapping("/sobre")
    public String sobre() {

      return "/sobre";
    }

}
