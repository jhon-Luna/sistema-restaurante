package com.sazonfuego.sistema.controller;

import com.sazonfuego.sistema.dto.request.UsuarioRequestDto;
import com.sazonfuego.sistema.dto.response.UsuarioResponseDto;
import com.sazonfuego.sistema.service.UsuarioService;
import jakarta.validation.Valid; // ¡NECESARIO PARA QUE FUNCIONEN TUS DTOS!
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuarios")
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDto>> obtenerTodosUsuarios(@PageableDefault(page = 0, size = 3) Pageable pageable){
        return ResponseEntity.ok(usuarioService.listarUsuarios(pageable));
    }


    @GetMapping("/buscar/{dni}")
    public ResponseEntity<UsuarioResponseDto> obtenerPorDni(@PathVariable String dni){
        return ResponseEntity.ok(usuarioService.obtenerUsuario(dni));
    }


    @PostMapping
    public ResponseEntity<UsuarioResponseDto> guardarUsuario(@Valid @RequestBody UsuarioRequestDto usuario){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.guardar(usuario));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> editarUsuario(@PathVariable int id, @Valid @RequestBody UsuarioRequestDto usuario){
        return ResponseEntity.ok(usuarioService.actualizar(id, usuario));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id){
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<Void> reactivarUsuario(@PathVariable int id){
        usuarioService.reactivar(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{id}/cambiar-contraseña")
    public ResponseEntity<UsuarioResponseDto> cambiarPassword(
            @PathVariable int id,
            @RequestParam String actual,
            @RequestParam String nueva) {
        return ResponseEntity.ok(usuarioService.cambiarContraseña(id, actual, nueva));
    }
}