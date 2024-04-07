package br.com.usystem.stockroll.security;

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


        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            
            http.authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/estoque")
                .permitAll()
            );
            
            // .anyRequest().permitAll() 
            // .anyRequest().authenticated() 

            return http.build();
        }




        @Bean
        WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web.ignoring().requestMatchers("/webjars/**");    
        }

        

        // RETORNAR ESSE MÉTODO PARA A CLASSE PasswordEncoderConfig
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }



}
