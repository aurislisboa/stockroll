package br.com.usystem.stockroll.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.models.Perfil;
import br.com.usystem.stockroll.models.Usuario;
import br.com.usystem.stockroll.repositories.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;



    @ModelAttribute("perfil")
    public Perfil[] getPerfil() {
        return Perfil.values();
    }


    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("usuario/listar");

        List<Usuario> usuarios = usuarioRepository.findAll();
        modelAndView.addObject("usuarios", usuarios);
        return modelAndView;
    }



    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("usuario/detalhar");

        Usuario usuario = usuarioRepository.getReferenceById(id);
        modelAndView.addObject("usuario", usuario);
        return modelAndView;
    }


    
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("usuario/formulario");

        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }



    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Usuario usuario) {
        ModelAndView modelAndView = new ModelAndView("redirect:/usuario");

        usuario.setCadastro(LocalDateTime.now());

         //System.out.println(usuario);


        usuarioRepository.save(usuario);
        return modelAndView;
    }


    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("usuario/formulario");
            modelAndView.addObject("usuario", usuarioRepository.getReferenceById(id));
        
        return modelAndView;
    }


    @PostMapping("/{id}/editar")
    public String editar(Usuario usuario) {

        usuario.setCadastro(LocalDateTime.now());

        usuarioRepository.save(usuario);

        return "redirect:/usuario";
    }


    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {

        usuarioRepository.deleteById(id);

        return "redirect:/usuario";
    }

}
