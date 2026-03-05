package com.sazonfuego.sistema.controller;


import com.sazonfuego.sistema.dto.request.UsuarioRequestDto;

import com.sazonfuego.sistema.dto.response.UsuarioResponseDto;
import com.sazonfuego.sistema.dtoAuth.AuthResponseDto;
import com.sazonfuego.sistema.dtoAuth.LoginRequestDto;
import com.sazonfuego.sistema.security.JwtSecurity;
import com.sazonfuego.sistema.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtSecurity jwtSecurity;
    private final UsuarioService usuarioService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreo(),
                        request.getContraseña()
                )
        );


        UserDetails user = userDetailsService.loadUserByUsername(request.getCorreo());


        String token = jwtSecurity.generarToken(user);


        return ResponseEntity.ok(new AuthResponseDto(token));
    }


    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDto> register(@RequestBody UsuarioRequestDto request) {
        return ResponseEntity.ok(usuarioService.guardar(request));
    }
}