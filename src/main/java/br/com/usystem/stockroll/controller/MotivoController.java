package br.com.usystem.stockroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.model.Motivo;
import br.com.usystem.stockroll.repository.MotivoRepository;


@Controller
@RequestMapping("/motivo") 
public class MotivoController {

    @Autowired
    private MotivoRepository motivoRepository;


    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("motivo/listar");
        
        modelAndView.addObject("motivos", motivoRepository.findAll(Sort.by("id")));
        return modelAndView;
    }



    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("motivo/detalhar");

        modelAndView.addObject("motivo", motivoRepository.getReferenceById(id));
        return modelAndView;
    }


    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("motivo/formulario");

        modelAndView.addObject("motivo", motivoRepository.getReferenceById(id));
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String editar(Motivo motivo) {

        motivoRepository.save(motivo);
        return "redirect:/motivo";
    }


    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("motivo/formulario");

        modelAndView.addObject("motivo", new Motivo());
        return modelAndView;
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(Motivo motivo) {

        motivoRepository.save(motivo);
        return "redirect:/motivo";
    }


    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {

        motivoRepository.deleteById(id);
        return "redirect:/usuario";
    }
    
}
