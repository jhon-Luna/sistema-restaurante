package com.sazonfuego.sistema.dtoAuth;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String correo;
    private String contraseña;
}
