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

import br.com.usystem.stockroll.dto.MotivoForm;
import br.com.usystem.stockroll.mappers.MotivoMapper;
import br.com.usystem.stockroll.model.Motivo;
import br.com.usystem.stockroll.repository.MotivoRepository;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/motivo") 
public class MotivoController {

    @Autowired
    private MotivoRepository motivoRepository;
    @Autowired
    private MotivoMapper mapper;


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



    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("motivo/formulario");

        modelAndView.addObject("form", new MotivoForm());
        return modelAndView;
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form") MotivoForm form, BindingResult result) {

        if(result.hasErrors()) return "motivo/formulario";

        Motivo motivo = mapper.toModel(form);
        // ------------------  criar uma validação para conferir se já existe um nome idêntico no banco.
        motivoRepository.save(motivo);
        return "redirect:/motivo";
    }

    
    
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Integer id) {
        var modelAndView = new ModelAndView("motivo/formulario");

        Motivo motivo = motivoRepository.getReferenceById(id);
        var form = mapper.toForm(motivo);
        modelAndView.addObject("form", form);
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String editar(@Valid @ModelAttribute("form") MotivoForm form, BindingResult result) {

        if(result.hasErrors()) return "motivo/formulario";

        Motivo motivo = mapper.toModel(form);
        motivoRepository.save(motivo);
        return "redirect:/motivo";
    }


    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {

        motivoRepository.deleteById(id);
        return "redirect:/usuario";
    }
    
}
