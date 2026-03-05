package com.sazonfuego.sistema.mapper;

import com.sazonfuego.sistema.dto.request.UsuarioRequestDto;
import com.sazonfuego.sistema.dto.response.UsuarioResponseDto;
import com.sazonfuego.sistema.modelo.entity.Usuarios;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public Usuarios toEntity(UsuarioRequestDto requestDto){
        if (requestDto == null) return null;
        Usuarios usuario = new Usuarios();
        usuario.setNombre(requestDto.getNombre());
        usuario.setApellido(requestDto.getApellido());
        usuario.setDni(requestDto.getDni());
        usuario.setCorreo(requestDto.getCorreo());
        usuario.setRol(requestDto.getRol());
        usuario.setFechaNacimiento(requestDto.getFechaNacimiento());
        usuario.setContraseña(requestDto.getContraseña());
        usuario.setFechaIngreso(LocalDate.now());
        usuario.setActivo(true);
        return usuario;
    }
    public UsuarioResponseDto toResponse(Usuarios entity){
        if (entity == null) return null;
        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setDni(entity.getDni());
        dto.setCorreo(entity.getCorreo());
        dto.setRol(entity.getRol());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setFechaIngreso(entity.getFechaIngreso());
        dto.setActivo(entity.isActivo());
        return dto;
    }

    public void actualizarEntity ( UsuarioRequestDto requestDto, Usuarios usuarioExistente ){
        usuarioExistente.setNombre(requestDto.getNombre());
        usuarioExistente.setApellido(requestDto.getApellido());
        usuarioExistente.setDni(requestDto.getDni());
        usuarioExistente.setCorreo(requestDto.getCorreo());
        usuarioExistente.setFechaNacimiento(requestDto.getFechaNacimiento());
        usuarioExistente.setRol(requestDto.getRol());

    }

    public List<UsuarioResponseDto> toResponseList(List<Usuarios> listEntity){
        if (listEntity == null) return null;
        return  listEntity.stream().map(this::toResponse).collect(Collectors.toList());
    }

}
