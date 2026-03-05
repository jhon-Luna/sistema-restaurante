package com.sazonfuego.sistema.useCase;

import com.sazonfuego.sistema.modelo.entity.Usuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsuariosUseCase extends CrudUseCase<Usuarios, Integer> {
    Usuarios obtenerPorId(int id);
}
