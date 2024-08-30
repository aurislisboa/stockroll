package br.com.usystem.stockroll.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.usystem.stockroll.model.Usuario;
import br.com.usystem.stockroll.repository.UsuarioRepository;

@Service
public class UsuarioService {


  @Autowired
  private UsuarioRepository usuarioRepository;


  public Usuario getUsuarioLogadoNoSistema(Principal principal) {
        return usuarioRepository.findByEmail(principal.getName()).orElseThrow();     // obtém o usuário logado no sistema.
        // return usuarioRepository.getReferenceById(4);                                   // desativar esse campo quando ativar o login.
    }












    
  
}//End-Class.
