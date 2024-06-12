package br.com.usystem.stockroll.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


        @Autowired
        private UserDetailsServiceImpl userDetailsServiceImpl;


        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            

            // Habilitar esse atributo para conseguir fazer o login.
            // você deve alterar o método o getUsuarioLogadoNoSistema(Principal principal) em UsuarioService.
    

            // http.authorizeHttpRequests(authorize -> authorize
            //     .anyRequest().authenticated()
            // )
            // .formLogin(login -> login
            //     .loginPage("/login")
            //     .defaultSuccessUrl("/estoque")
            //     .permitAll()
            // )
            // .logout(logout -> logout
            // .logoutSuccessUrl("/"));
            



            // .anyRequest().permitAll() 
            // .anyRequest().authenticated() 


            return http.build();
        }




        @Bean
        WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web.ignoring().requestMatchers("/webjars/**");    
        }

        

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }





        
        
        // @Bean 
        // public AuthenticationManager authenticationManager(HttpSecurity http, 
        // BCryptPasswordEncoder passwordEncoder, 
        // UserDetailsService userDetailServiceImpl) throws Exception {
        
        //     return http.getSharedObject(AuthenticationManagerBuilder.class)
        //     .userDetailsService(userDetailsServiceImpl)
        //     .passwordEncoder(passwordEncoder)
        //     .and()
        //     .build();
        
        // }





}
