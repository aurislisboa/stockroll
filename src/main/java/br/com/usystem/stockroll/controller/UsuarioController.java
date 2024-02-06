package br.com.usystem.stockroll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.usystem.stockroll.usuario.Usuario;
import br.com.usystem.stockroll.usuario.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;



    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("usuario/listar.html");

        List<Usuario> usuarios = usuarioRepository.findAll();
        modelAndView.addObject("usuarios", usuarios);
        return modelAndView;
    }



    @GetMapping("/{id}")
    public ModelAndView detalhar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("usuario/detalhar.html");

        Usuario usuario = usuarioRepository.getReferenceById(id);
        modelAndView.addObject("usuario", usuario);
        return modelAndView;
    }


    
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("usuario/cadastro.html");

        modelAndView.addObject("usuario", new Usuario());
        return modelAndView;
    }



    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(Usuario usuario) {
        ModelAndView modelAndView = new ModelAndView("redirect:/usuario");

        usuarioRepository.save(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
        System.out.println(usuario);
        return modelAndView;
    }




}
