package com.sazonfuego.sistema.modelo.entity;
import com.sazonfuego.sistema.modelo.Enum.RolUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Usuario")
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "dni")
    private String dni;
    @Column(name = "correo")
    private String correo;
    @Column(name = "contraseña")
    private String contraseña;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;
    @Enumerated(EnumType.STRING)
    private RolUsuario rol;
    @Column(name = "activo")
    private boolean activo;

    @OneToMany(mappedBy = "idUsuario")
    private List<Pedidos> pedidos = new ArrayList<>();

}
