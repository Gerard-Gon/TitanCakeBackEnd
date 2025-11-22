package com.titancake.TitanCake.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titancake.TitanCake.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {


    List<Carrito> findByUsuarioId(Integer usuarioId);
    
}
