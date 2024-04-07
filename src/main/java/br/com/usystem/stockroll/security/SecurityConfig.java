// package br.com.usystem.stockroll.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {


//         SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            
//             http.authorizeHttpRequests(authorize -> authorize
//                 .anyRequest().permitAll()
//             );
            
//             // .anyRequest().permitAll() 
//             // .anyRequest().authenticated() 

//             return http.build();
//         }




//         @Bean
//         WebSecurityCustomizer webSecurityCustomizer() {
//             return (web) -> web.ignoring().requestMatchers("/webjars/**");    
//         }

        

// }
