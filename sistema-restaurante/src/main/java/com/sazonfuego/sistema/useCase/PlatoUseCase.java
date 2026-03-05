package com.sazonfuego.sistema.useCase;

import com.sazonfuego.sistema.modelo.entity.Platos;
import org.springframework.stereotype.Component;

@Component
public interface PlatoUseCase extends  CrudUseCase<Platos,Integer> {
}
