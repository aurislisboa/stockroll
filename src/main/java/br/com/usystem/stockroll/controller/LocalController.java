package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.models.Local;
import br.com.usystem.stockroll.repositories.LocalRepository;

@Controller
@RequestMapping("/local")
public class LocalController {
    
    @Autowired
    private LocalRepository localRepository;

    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("local/listar");

        var locais = localRepository.findAll(Sort.by("id"));
            locais.remove(0);
        modelAndView.addObject("locais", locais);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("local/detalhar");

        modelAndView.addObject("local", localRepository.getReferenceById(id));
        return modelAndView;
    }


    @GetMapping("/cadastrar") 
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("local/formulario");

        modelAndView.addObject("local", new Local());
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Local local) {
        localRepository.save(local);

        return "redirect:/local";
    }


    
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("local/formulario");
        
        modelAndView.addObject("local", localRepository.getReferenceById(id));
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String editar(Local local) {
        localRepository.save(local);

        return "redirect:/local";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        localRepository.deleteById(id);
        
        return "redirect:/local";
    }


}
