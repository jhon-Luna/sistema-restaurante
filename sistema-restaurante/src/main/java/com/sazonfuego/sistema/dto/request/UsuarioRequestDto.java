package com.sazonfuego.sistema.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sazonfuego.sistema.modelo.Enum.RolUsuario;
import lombok.Data;
import jakarta.validation.constraints.*;


import java.time.LocalDate;

@Data
public class UsuarioRequestDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;
    @NotBlank(message = "El NI es obligatorio")
    @Size(min = 8,max=8,message = "El DNI debe de tener 8 caracteres")
    @Pattern(regexp = "\\d+",message = "El Dni solo debe de tener numeros")
    private String dni;
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es valido")
    private String correo;
    @NotBlank(message = "La contraseña es obligatoria ")
    @Size(min = 6 ,message = "La contraseña minimo debe de tener 6 caracteres")
    private String contraseña;
    @NotNull (message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe de ser en el pasado")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @NotNull(message = "El rol es obligatorio")
    private RolUsuario rol;

}
