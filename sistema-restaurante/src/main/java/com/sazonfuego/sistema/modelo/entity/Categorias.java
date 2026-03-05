package com.sazonfuego.sistema.modelo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @ColumnDefault("1")
    @Column(name = "activa")
    private Boolean activa;

    @OneToMany(mappedBy = "idCategoria")
    private List<Platos> platos = new ArrayList<>();

}
