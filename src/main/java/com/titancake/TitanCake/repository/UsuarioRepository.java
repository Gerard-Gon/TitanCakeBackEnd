package com.titancake.TitanCake.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.titancake.TitanCake.model.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByCorreo(String correo);
}
