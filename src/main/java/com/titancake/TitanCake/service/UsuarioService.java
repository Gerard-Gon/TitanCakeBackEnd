package com.titancake.TitanCake.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.titancake.TitanCake.model.Usuario;
import com.titancake.TitanCake.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuario() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario patchUsuario(Long id, Usuario parcialUsuario){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {

            Usuario usuarioToUpdate = usuarioOptional.get();

            if (parcialUsuario.getNombreUsuario() != null) {
                usuarioToUpdate.setNombreUsuario(parcialUsuario.getNombreUsuario());
            }

            if(parcialUsuario.getPassword() != null){
                usuarioToUpdate.setPassword(parcialUsuario.getPassword());
            }
            if(parcialUsuario.getCorreo()!=null){
                usuarioToUpdate.setCorreo(parcialUsuario.getCorreo());
            }

            return usuarioRepository.save(usuarioToUpdate);
        } else {
            return null; // or throw an exception
        }

    }

    
}
