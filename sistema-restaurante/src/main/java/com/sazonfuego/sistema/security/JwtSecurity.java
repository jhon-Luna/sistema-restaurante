package com.sazonfuego.sistema.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtSecurity {

    private static final String LLAVE_SECRETA = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";


    public String generarToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 Horas
                .signWith(obtenerLlave(), SignatureAlgorithm.HS256)
                .compact();
    }


    public boolean esTokenValido(String token, UserDetails user) {
        final String username = extraerClaim(token, Claims::getSubject);
        return (username.equals(user.getUsername()) && !esTokenVencido(token));
    }


    public String extraerUsername(String token) {
        return extraerClaim(token, Claims::getSubject);
    }



    private boolean esTokenVencido(String token) {
        return extraerClaim(token, Claims::getExpiration).before(new Date());
    }


    public <T> T extraerClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(obtenerLlave())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    private Key obtenerLlave() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(LLAVE_SECRETA));
    }
}