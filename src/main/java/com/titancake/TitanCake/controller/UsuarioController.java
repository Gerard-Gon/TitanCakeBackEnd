package com.titancake.TitanCake.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.titancake.TitanCake.model.Usuario;
import com.titancake.TitanCake.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Muestra una lista de los usuarios registrados")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuario();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por su id")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar un usuario parcialmente")
    public ResponseEntity<Usuario> patchUsuario(@PathVariable Long id , @RequestBody Usuario parcialUsuario){
        try {
            Usuario updateUsuario = usuarioService.patchUsuario(id, parcialUsuario);
            return ResponseEntity.ok(updateUsuario);
            
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //En caso de error revisar esta parte
    @PostMapping
    @Operation(summary = "Agrega un usuario")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario createdUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(201).body(createdUsuario);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario existingUsuario = usuarioService.getUsuarioById(id);
        if (existingUsuario != null) {
            existingUsuario.setNombreUsuario(usuario.getNombreUsuario());
            existingUsuario.setCorreo(usuario.getCorreo());
            existingUsuario.setPassword(usuario.getPassword());
            return usuarioService.saveUsuario(existingUsuario);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario")
    public void deleteUsuario(@PathVariable Long id) {
        
    }






    
}
