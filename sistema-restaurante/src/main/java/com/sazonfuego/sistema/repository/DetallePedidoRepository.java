package com.sazonfuego.sistema.repository;

import com.sazonfuego.sistema.modelo.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido,Integer> {
}
