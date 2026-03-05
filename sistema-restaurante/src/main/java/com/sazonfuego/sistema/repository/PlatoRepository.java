package com.sazonfuego.sistema.repository;

import com.sazonfuego.sistema.modelo.entity.Platos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends JpaRepository<Platos,Integer> {
}
