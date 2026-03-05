package com.sazonfuego.sistema.useCase;

import com.sazonfuego.sistema.modelo.entity.Usuarios;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CrudUseCase<T,ID> {
 List<T>listarTodos();
 T obtenerPorId(ID id);
 void guardar(T entity);
 void eliminar(ID id);
 void actualizar(T entity);


}
