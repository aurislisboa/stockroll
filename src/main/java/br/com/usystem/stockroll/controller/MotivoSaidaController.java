package br.com.usystem.stockroll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.models.MotivoSaida;
import br.com.usystem.stockroll.models.Usuario;
import br.com.usystem.stockroll.repositories.MotivoSaidaRepository;


@Controller
@RequestMapping("/motivo") 
public class MotivoSaidaController {

    @Autowired
    private MotivoSaidaRepository motivoSaidaRepository;


    @GetMapping
    public ModelAndView listar() {
        var modelAndView = new ModelAndView("motivo/listar");
        
        modelAndView.addObject("motivos", motivoSaidaRepository.findAll(Sort.by("id")));
        return modelAndView;
    }



    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("motivo/detalhar");

        modelAndView.addObject("motivo", motivoSaidaRepository.getReferenceById(id));
        return modelAndView;
    }


    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("motivo/formulario");

        modelAndView.addObject("motivo", motivoSaidaRepository.getReferenceById(id));
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String editar(MotivoSaida motivo) {

        motivoSaidaRepository.save(motivo);
        return "redirect:/motivo";
    }


    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("motivo/formulario");

        modelAndView.addObject("motivo", new MotivoSaida());
        return modelAndView;
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(MotivoSaida motivo) {

        motivoSaidaRepository.save(motivo);
        return "redirect:/motivo";
    }


    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {

        motivoSaidaRepository.deleteById(id);

        return "redirect:/usuario";
    }
    
}
