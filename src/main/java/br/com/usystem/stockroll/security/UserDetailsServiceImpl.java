// package br.com.usystem.stockroll.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import br.com.usystem.stockroll.models.Usuario;
// import br.com.usystem.stockroll.repositories.UsuarioRepository;


// @Service
// public class UserDetailsServiceImpl implements UserDetailsService {

//     @Autowired
//     private UsuarioRepository usuarioRepository; 

//     @Override
//     public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
//         Usuario usuario = usuarioRepository.findByNome(nome)
//                 .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
//         return new UserDetailsImpl(usuario);  
//     }

// }
