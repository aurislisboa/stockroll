package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.dto.LocalForm;
import br.com.usystem.stockroll.mappers.LocalMapper;
import br.com.usystem.stockroll.model.Local;
import br.com.usystem.stockroll.repository.LocalRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/local")
public class LocalController {
    
    @Autowired
    private LocalRepository localRepository;
    @Autowired  
    private LocalMapper mapper;

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

        modelAndView.addObject("form", new LocalForm());
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form") LocalForm form, BindingResult result) {

        if(result.hasErrors()) return "local/formulario";

        Local local = mapper.toModel(form);
        localRepository.save(local);

        return "redirect:/local";
    }


    
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("local/formulario");
            Local local = localRepository.getReferenceById(id);
            var form = mapper.toForm(local);
            modelAndView.addObject("form", form);
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String editar(@Valid @ModelAttribute("form") LocalForm form, BindingResult result) {
        
        if(result.hasErrors()) return "local/formulario";
        
        Local local = mapper.toModel(form);
        localRepository.save(local);

        return "redirect:/local";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        localRepository.deleteById(id);
        
        return "redirect:/local";
    }


}
