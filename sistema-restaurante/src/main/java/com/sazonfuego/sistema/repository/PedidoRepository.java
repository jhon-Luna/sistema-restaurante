package com.sazonfuego.sistema.repository;

import com.sazonfuego.sistema.modelo.entity.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedidos,Integer> {
}
