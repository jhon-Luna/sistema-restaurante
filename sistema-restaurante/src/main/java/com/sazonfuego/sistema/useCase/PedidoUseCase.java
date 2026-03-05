package com.sazonfuego.sistema.useCase;

import com.sazonfuego.sistema.modelo.entity.Pedidos;
import org.springframework.stereotype.Component;

@Component
public interface PedidoUseCase extends  CrudUseCase<Pedidos,Integer> {
}
