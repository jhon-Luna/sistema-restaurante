package com.sazonfuego.sistema.repository;

import com.sazonfuego.sistema.modelo.entity.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categorias,Integer> {
}
