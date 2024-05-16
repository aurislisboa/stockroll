package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.repositories.ProdutoRepository;

@Controller
@RequestMapping
public class HomeController {


	@Autowired
    ProdutoRepository produtoRepository;
    
    //@RequestMapping(path = "/", method = RequestMethod.GET)
    @GetMapping
    public ModelAndView home() {
		var modelAndView = new ModelAndView("/home");

		// Integer totalEstoque = produtoRepository.selectSumQtdEstoque();
    //     modelAndView.addObject("totalEstoque", totalEstoque);
		
        return modelAndView;
    }


    @GetMapping("/sobre")
    public String sobre() {

      return "/sobre";
    }

}
