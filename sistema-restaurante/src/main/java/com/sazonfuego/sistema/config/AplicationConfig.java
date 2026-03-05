package com.sazonfuego.sistema.config;

import com.sazonfuego.sistema.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AplicationConfig {

    private final UsuarioRepository usuarioRepository;


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            var usuario = usuarioRepository.findByCorreo(username);
            if (usuario == null) throw new UsernameNotFoundException("Usuario no encontrado");
            return User.builder()
                    .username(usuario.getCorreo())
                    .password(usuario.getContraseña())
                    .roles(usuario.getRol().name())
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}