package com.sazonfuego.sistema.repository;

import com.sazonfuego.sistema.modelo.entity.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    Usuarios findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    Usuarios findByDni(String dni);
    boolean  existsByDni(String dni);
    Page<Usuarios> findByActivoTrue(Pageable pageable);
}
