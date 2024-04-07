// package br.com.usystem.stockroll.security;

// import java.util.Collection;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import br.com.usystem.stockroll.models.Usuario;

// public class UserDetailsImpl implements UserDetails {


//     private final Usuario usuario;


//     public UserDetailsImpl(Usuario usuario) {
//         this.usuario = usuario;
//     }


//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
//     }

//     @Override
//     public String getPassword() {
//         return this.usuario.getSenha();
//     }

//     @Override
//     public String getUsername() {
//         return this.usuario.getSenha();
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }

// }
