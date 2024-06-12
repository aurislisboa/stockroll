package br.com.usystem.stockroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping 
public class HomeController {

  
   
    //@RequestMapping(path = "/", method = RequestMethod.GET)
    @GetMapping
    public ModelAndView home() {
		var modelAndView = new ModelAndView("/home");
    
        return modelAndView;
    }




    @GetMapping("/sobre")
    public String sobre() {
      return "/sobre";
    }




}
