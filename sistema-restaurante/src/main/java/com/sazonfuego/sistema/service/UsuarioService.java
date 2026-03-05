package com.sazonfuego.sistema.service;

import com.sazonfuego.sistema.dto.request.UsuarioRequestDto;
import com.sazonfuego.sistema.dto.response.UsuarioResponseDto;

import com.sazonfuego.sistema.exepcion.RecursoNoEncontradoException;
import com.sazonfuego.sistema.exepcion.SolicitudIncorrectaException;
import com.sazonfuego.sistema.mapper.UsuarioMapper;
import com.sazonfuego.sistema.modelo.entity.Usuarios;
import com.sazonfuego.sistema.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService  {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDto guardar(UsuarioRequestDto entity) {
        // CAMBIO: Usamos SolicitudIncorrectaException para duplicados
        if (usuarioRepository.existsByCorreo(entity.getCorreo())) {
            throw new SolicitudIncorrectaException("El correo " + entity.getCorreo() + " ya existe");
        }
        if (usuarioRepository.existsByDni(entity.getDni())) {
            throw new SolicitudIncorrectaException("El DNI " + entity.getDni() + " ya existe");
        }

        Usuarios usuarioNuevo = usuarioMapper.toEntity(entity);
        usuarioNuevo.setContraseña(passwordEncoder.encode(entity.getContraseña()));
        Usuarios usuariosGuardado = usuarioRepository.save(usuarioNuevo);

        return usuarioMapper.toResponse(usuariosGuardado);
    }


    public UsuarioResponseDto actualizar(int id, UsuarioRequestDto requestDto) {
        Usuarios usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario con ID " + id + " no encontrado"));

        if (!usuarioExistente.getCorreo().equals(requestDto.getCorreo()) &&
                usuarioRepository.existsByCorreo(requestDto.getCorreo())) {
            throw new SolicitudIncorrectaException("El correo " + requestDto.getCorreo() + " ya está en uso por otro usuario");
        }
        String passActual = usuarioExistente.getContraseña();

        usuarioMapper.actualizarEntity(requestDto, usuarioExistente);
        if (requestDto.getContraseña() != null && !requestDto.getContraseña().isBlank()) {
            usuarioExistente.setContraseña(passwordEncoder.encode(requestDto.getContraseña()));
        } else {
            usuarioExistente.setContraseña(passActual);
        }

        Usuarios usuarioActual = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toResponse(usuarioActual);
    }
    public UsuarioResponseDto cambiarContraseña (int id, String contraseñaActual, String nuevaContraseña) {
        Usuarios usuario = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));
        if (!passwordEncoder.matches(contraseñaActual,usuario.getContraseña())){
            throw new SolicitudIncorrectaException("Las contraseñas no coinciden");
        }
        usuario.setContraseña(passwordEncoder.encode(nuevaContraseña));
        usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuario);

    }

    public void eliminar(int id) {
        // CAMBIO: Usamos RecursoNoEncontradoException
        Usuarios usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario con ID " + id + " no encontrado"));

        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }
    public void reactivar(int  id ){
    Usuarios usuario =usuarioRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Usuario con ID " + id + " no encontrado"));
    usuario.setActivo(true);
       usuarioRepository.save(usuario);


    }

    public Page<UsuarioResponseDto> listarUsuarios(Pageable pageable){
        Page<Usuarios> usuarios = usuarioRepository.findByActivoTrue(pageable);
        return usuarios.map(usuario -> usuarioMapper.toResponse(usuario));
    }

    public UsuarioResponseDto obtenerUsuario(String dni){
        Usuarios usuario=usuarioRepository.findByDni(dni);
        if (usuario!=null) {
            return usuarioMapper.toResponse(usuario);
        }
        else {
            throw  new  RecursoNoEncontradoException("No existe un usuario con el dni: "+dni);
        }

    }
}