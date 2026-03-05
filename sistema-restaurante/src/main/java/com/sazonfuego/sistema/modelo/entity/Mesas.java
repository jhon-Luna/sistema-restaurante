package com.sazonfuego.sistema.modelo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mesas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa")
    private int id;
    @Column(name = "numero_mesa")
    private int numeroMesa;
    @Column(name = "capacidad")
    private int capacidad;
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "idMesa")
    private List<Pedidos> pedidos = new ArrayList<>();

}
