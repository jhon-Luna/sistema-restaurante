package com.sazonfuego.sistema.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sazonfuego.sistema.modelo.Enum.RolUsuario;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UsuarioResponseDto {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String correo;
    @JsonFormat(pattern = "YYYY/MM/dd")
    private LocalDate fechaNacimiento;
    @JsonFormat(pattern = "YYYY/MM/dd")
    private LocalDate fechaIngreso;
    private RolUsuario rol;
    private boolean activo;
}
