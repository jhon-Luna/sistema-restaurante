package com.sazonfuego.sistema.useCase;

import com.sazonfuego.sistema.modelo.entity.Categorias;
import org.springframework.stereotype.Component;

@Component
public interface CategoriaUseCase extends CrudUseCase<Categorias,Integer> {
}
