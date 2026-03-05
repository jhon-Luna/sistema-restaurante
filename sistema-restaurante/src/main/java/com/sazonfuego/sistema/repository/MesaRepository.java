package com.sazonfuego.sistema.repository;

import com.sazonfuego.sistema.modelo.entity.Mesas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesas,Integer> {
}
