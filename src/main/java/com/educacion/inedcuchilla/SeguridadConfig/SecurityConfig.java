package com.educacion.inedcuchilla.SeguridadConfig;


import com.educacion.inedcuchilla.filtro.JWTFiltro;
import com.educacion.inedcuchilla.filtro.ValidacionFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;


    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests( (authz) -> authz
                        .requestMatchers("/login").permitAll()
//                        .requestMatchers("/usuarios/**").permitAll()
                        .requestMatchers("/usuarios/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers("/pagos/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers("/detalle-pago/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .requestMatchers("/materias/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                        .anyRequest().authenticated())
                        .addFilter(new JWTFiltro(authenticationManager())) // comentar para crear usuario admin
                        .addFilter(new ValidacionFiltro(authenticationManager())) //comentar para crear usuario admin
                        .csrf(config -> config.disable())
                        .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .build();
    }


}
