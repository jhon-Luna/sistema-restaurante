package com.sazonfuego.sistema.useCase;

import com.sazonfuego.sistema.modelo.entity.Mesas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface MesaUseCase extends CrudRepository<Mesas,Integer> {
}
