package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.repositories.LocalRepository;

@Controller
@RequestMapping("local")
public class LocalController {
    
    @Autowired
    private LocalRepository localRepository;


    public ModelAndView listar() {
        var modelAndView = new ModelAndView("local/listar");

        modelAndView.addObject("locais", localRepository.findAll());
        return modelAndView;
    }

}
